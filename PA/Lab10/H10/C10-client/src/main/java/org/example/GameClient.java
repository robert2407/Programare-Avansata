package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameClient {
    private String host;
    private int port;

    public GameClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        try (Socket socket = new Socket(host, port);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader stdInput = new BufferedReader(new InputStreamReader(System.in))) {

            String userInput;
            while ((userInput = stdInput.readLine()) != null) {
                output.println(userInput);
                String serverResponse = input.readLine();
                System.out.println("Server response: " + serverResponse);

                if (userInput.equals("exit")) {
                    break;
                }
            }
        } catch (UnknownHostException ex) {
            System.err.println("Error: on host " + host);
        } catch (IOException ex) {
            System.err.println("Error on server " + ex.getMessage());
        }
    }
}
