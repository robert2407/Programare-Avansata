package org.example;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Player currentPlayer;
    private boolean isStarted;
    private boolean isGameOver;
    private Board board;
    private org.example.Timer timer;

    public Game() {
        players = new ArrayList<>();
        currentPlayer = null;
        isStarted = false;
        isGameOver = false;
        board = new Board();
    }

    public boolean addPlayer(Player player) {
        return (players.add(player));
    }

    public void startGame() {
        if (players.size() == 2 && !isStarted) {
            currentPlayer = players.get(0);
            isStarted = true;
            timer = new Timer();    // 1 minut la dispozitie sa joace
            timer.start();
        }
    }


    public boolean makeMove(int row, int col) {
        if (isGameOver() || currentPlayer == null) {
            return false;
        }

        if (timer.isTimeUp()) {
            isGameOver = true;
            return false;
        }

        Player player = currentPlayer;
        if (board.isValidMove(row, col)) {
            board.placeMove(row, col, player);
            System.out.println(getBoardAsString()); //afisez matricea la server
            return true;
        }
        return false;
    }


    public boolean isGameOver() {
        if (isGameOver) {
            return true;
        }

        if (board.hasStraightLineOfLength(5)) {
            isGameOver = true;
            return true;
        }

        if (board.isFull()) {
            isGameOver = true;
            return true;
        }

        return false;
    }

    public void endGame() {
        isGameOver = true;
    }

    public void switchTurn() {
        if (currentPlayer == players.get(0)) {
            currentPlayer = players.get(1);
        } else {
            currentPlayer = players.get(0);
        }
        timer.stop();
    }

    public synchronized boolean isGameFull() {
        return players.size() == 2;
    }

    public synchronized Player getCurrentPlayer() {
        return currentPlayer;
    }

    public synchronized List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public String getBoardAsString() {
        return board.toString();
    }
}
