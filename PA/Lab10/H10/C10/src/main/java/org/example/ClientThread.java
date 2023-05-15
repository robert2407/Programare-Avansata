package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientThread extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Game game;
    private boolean isStarted;
    private List<Player> players;
    private Player currentPlayer;

    public ClientThread(Socket socket, Game game) {
        this.clientSocket = socket;
        this.game = game;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ex) {
            System.err.println("Error -> " + ex.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String input;
            while ((input = in.readLine()) != null) {
                if (input.equals("stop")) {
                    break;
                }

                String response = processInput(input);
                out.println(response);
            }

            out.println("Server oprit");
            clientSocket.close();
            System.out.println("Client deconectat");
        } catch (IOException ex) {
            System.err.println("Error -> " + ex.getMessage());
        }
    }

    private String processInput(String input) {
        String[] parts = input.split(" ");
        String command = parts[0];

        if (command.equals("create")) {
            return createGame();
        } else if (command.equals("join")) {
            return joinGame();
        } else if (command.equals("move")) {
            return makeMove(parts);
        } else {
            return "Comanda invalida";
        }
    }


    private String createGame() {
        if (game.isGameFull()) {
            return "Nr maxim de jucatori logati deja.";
        }

        Player player = new Player("Player");
        if (game.addPlayer(player)) {
            if (game.getPlayers().size() == 2 && !isStarted) {
                game.startGame();
//                isStarted = true;
            }
            return "Ai intrat. Esti jucatorul numarul " + game.getPlayers().size();
        } else {
            return "Nr maxim de jucatori logati";
        }
    }

    private String joinGame() {
        if (game.isGameFull()) {
            return "Nr maxim de jucatori logati deja.";
        }

        Player player = new Player("Player");
        if (game.addPlayer(player)) {
            if (game.getPlayers().size() == 2) {
                game.startGame();
//                isStarted = true;
            }
            return "Ai intrat. Esti jucatorul numarul " + game.getPlayers().size();
        } else {
            return "Nr maxim de jucatori logati";
        }
    }

    private String makeMove(String[] parts) {
        if (parts.length < 3) {
            return "Mutare gresita -> 'move <row> <column>'";
        }

        if (game.isGameFull()) {
            Player currentPlayer = game.getCurrentPlayer();
            if (currentPlayer == null || !currentPlayer.getName().equals("Player")) {
                return "Nu e randul tau sa faci o mutare";
            }

            int row, col;
            try {
                row = Integer.parseInt(parts[1]);
                col = Integer.parseInt(parts[2]);
            } catch (NumberFormatException e) {
                return "Mutare gresita";
            }

            if (game.makeMove(row, col)) {
                if (game.isGameOver()) {
                    game.endGame();
                    return "Joc incheiat. Castigator -> " + currentPlayer.getName();
                } else {
                    game.switchTurn();
                    int currentPlayerNumber = game.getPlayers().indexOf(game.getCurrentPlayer()) + 1;
                    return "Mutare efectuata. Randul jucatorului -> " + currentPlayerNumber;
                }
            } else {
                return "Mutare gresita";
            }
        } else {
            return "Asteptam sa mai intre un jucator...";
        }
    }

}
