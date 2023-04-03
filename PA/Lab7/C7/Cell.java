package org.example;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private boolean visited;
    private List<Token> tokens;

    public Cell() {
        visited = false;
        tokens = new ArrayList<>();
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public boolean isVisited() {
        return visited;
    }
}