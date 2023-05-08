package org.example;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class GameServer {
    private int port;
    private boolean isRunning;

    public GameServer(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            isRunning = true;
            System.out.println("Server started on port " + port);

            while (isRunning) {
                System.out.println("Waiting for clients...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostName());

                ClientThread clientThread = new ClientThread(clientSocket);
                clientThread.start();
            }
        } catch (IOException ex) {
            System.err.println("Server error! " + ex.getMessage());
        }
    }
}

