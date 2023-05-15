package org.example;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class GameServer {
    private int port;
    private boolean isRunning;
    private ServerSocket serverSocket;
    private Game game;

    public GameServer(int port) {
        this.port = port;
        this.game = new Game();
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            System.err.println("Server error " + ex.getMessage());
        }
    }

    public void start() {
        try {
            isRunning = true;
            System.out.println("Server deschis la " + port);

            while (isRunning) {
                System.out.println("Astept jucatori");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client conectat " + clientSocket.getInetAddress().getHostName());

                ClientThread clientThread = new ClientThread(clientSocket, game);
                clientThread.start();
            }
        } catch (IOException ex) {
            System.err.println("Server error " + ex.getMessage());
        }
    }

    public void stop() {
        isRunning = false;
        try {
            serverSocket.close();
        } catch (IOException ex) {
            System.err.println("Error socket " + ex.getMessage());
        }
    }
}
