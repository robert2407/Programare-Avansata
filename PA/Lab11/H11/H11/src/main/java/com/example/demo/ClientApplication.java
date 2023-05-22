package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);

        String baseUrl = "http://localhost:8081";
        Player player = new Player("John");
        ResponseEntity<Player> playerResponse = new RestTemplate().postForEntity(baseUrl + "/players", player, Player.class);
        Player addedPlayer = playerResponse.getBody();
        System.out.println("Jucator adaugat -> " + addedPlayer.getName());
        addedPlayer.setName("John Doe");

        HttpEntity<Player> updateRequest = new HttpEntity<>(addedPlayer);
        ResponseEntity<Player> updateResponse = new RestTemplate().exchange(baseUrl + "/players/{name}",
                HttpMethod.PUT, updateRequest, Player.class, addedPlayer.getName());

        Player updatedPlayer = updateResponse.getBody();

        System.out.println("Jucator cu nume schimbat -> " + updatedPlayer.getName());

        ResponseEntity<Player[]> allPlayersResponse = new RestTemplate().getForEntity(baseUrl + "/players", Player[].class);
        Player[] players = allPlayersResponse.getBody();

        System.out.println("Acestia sunt jucatorii ->");
        for (Player p : players) {
            System.out.println(p.getName());
        }
        
        String playerDelte = "John Doe";
        new RestTemplate().delete(baseUrl + "/players/{name}", playerDelte);
        System.out.println("Jucator sters.");
        String playerName = "Alice";

        ResponseEntity<Game[]> gamesResponse = new RestTemplate().getForEntity(baseUrl + "/players/{name}/games",
                Game[].class, playerName);

        Game[] games = gamesResponse.getBody();
        System.out.println("Jocurile lui alice ->");
        for (Game game : games) {
            System.out.println("Nume " + game.getName() + ", Data: " + game.getDate() + ", Scor: " + game.getScore());
        }
    }
}
