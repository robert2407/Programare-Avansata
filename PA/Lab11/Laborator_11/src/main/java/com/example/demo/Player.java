package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Game> games;

    public Player() {
        this.games = new ArrayList<>();
    }

    public Player(String name) {
        this.name = name;
        this.games = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGames() {
        return games;
    }
}
