package org.example;

public class Main {
    public static void main(String[] args) {
        GameClient client = new GameClient("localhost", 9999);
        client.start();
    }
}
