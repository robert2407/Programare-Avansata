package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/players")
class PlayerController {

    private List<Player> playerList = new ArrayList<>();        //lista de jucatori

    @GetMapping     //pt afisarea tuturor jcuatorilor
    public List<Player> getAllPlayers() {
        return playerList;
    }

    @PostMapping        //adaug jucatori
    public Player addPlayer(@RequestBody Player player) {
        playerList.add(player);
        return player;
    }

    @PutMapping("/{name}")      //folosiut pentru a updata numele unui jucator
    public ResponseEntity<Player> updatePlayerName(@PathVariable String name, @RequestBody Player updatedPlayer) {
        for (Player player : playerList) {
            if (player.getName().equals(name)) {
                player.setName(updatedPlayer.getName());
                return ResponseEntity.ok(player);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{name}")       //folosit pentru a sterge un jucator
    public ResponseEntity<Void> deletePlayer(@PathVariable String name) {
        Player playerToRemove = null;
        for (Player player : playerList) {
            if (player.getName().equals(name)) {
                playerToRemove = player;
                break;
            }
        }
        if (playerToRemove != null) {
            playerList.remove(playerToRemove);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{name}/games")        //sa vad ce jocuri are initiate fiecare jucator
    public ResponseEntity<List<Game>> getPlayerGames(@PathVariable String name) {
        for (Player player : playerList) {
            if (player.getName().equals(name)) {
                return ResponseEntity.ok(player.getGames());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
