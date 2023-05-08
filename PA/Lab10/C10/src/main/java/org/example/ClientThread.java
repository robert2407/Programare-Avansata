package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientThread(Socket socket) {
        this.clientSocket = socket;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException ex) {
            System.err.println("Error on stream! " + ex.getMessage());
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
                System.out.println("Server received the request: " + input);
                out.println("Server received the request: " + input);
            }

            out.println("Server stopped");
            clientSocket.close();
            System.out.println("Client disconnected");
        } catch (IOException ex) {
            System.err.println("Error communicating with client: " + ex.getMessage());
        }
    }
}
