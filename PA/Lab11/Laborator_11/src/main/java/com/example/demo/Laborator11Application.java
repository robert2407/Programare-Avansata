package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Laborator11Application {

	public static void main(String[] args) {
		SpringApplication.run(Laborator11Application.class, args);

		String url = "http://localhost:8081/players";		//8081 pentru ca 8080 nu merge
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Player player1 = new Player("John");
		HttpEntity<Player> requestEntity1 = new HttpEntity<>(player1, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Player> response1 = restTemplate.exchange(url, HttpMethod.POST, requestEntity1, Player.class);

		Player addedPlayer1 = response1.getBody();

		System.out.println("Primul jucator -> " + addedPlayer1.getName());

		Player player2 = new Player("Alice");
		HttpEntity<Player> requestEntity2 = new HttpEntity<>(player2, headers);
		ResponseEntity<Player> response2 = restTemplate.exchange(url, HttpMethod.POST, requestEntity2, Player.class);
		Player addedPlayer2 = response2.getBody();
		System.out.println("Al doilea jucator -> " + addedPlayer2.getName());

		// pentru a sterge un jcuator
		String deleteUrl = "http://localhost:8081/players/{name}";
		String player = "John";
		ResponseEntity<Void> deleteResponse = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, Void.class, player);
		if (deleteResponse.getStatusCode().is2xxSuccessful()) {
			System.out.println("Jucator eliminat");
		} else {
			System.out.println("Error!");
		}

		String playerName = "Alice";
		Game game = new Game("Chess", new Date(), 100);
		HttpEntity<Game> requestEntity3 = new HttpEntity<>(game, headers);
		String addGameUrl = "http://localhost:8081/players/" + playerName + "/games";
		ResponseEntity<Game> addGameResponse = restTemplate.exchange(addGameUrl, HttpMethod.POST, requestEntity3, Game.class);
		Game addedGame = addGameResponse.getBody();
		System.out.println("Joc adaugat ->" + addedGame.getName() + ", Data -> " + addedGame.getDate() + ", Score -> " + addedGame.getScore());
	}
}
