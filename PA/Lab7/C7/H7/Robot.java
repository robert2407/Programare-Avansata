package org.example;

import java.awt.*;
import java.util.Random;

public class Robot implements Runnable {
    public String name;
    public boolean running;
    public boolean paused;
    public long pauseTime;
    public Exploration explore;
    private int currentRow;
    private int currentColumn;
    private int nextDirection;
    public int numTokens;

    public Robot(String name) {
        this.name = name;
        this.paused = false;
        this.pauseTime = 0;
        this.nextDirection = -1; // initialize to invalid value
        this.numTokens = 0;
    }

    public void setExplore(Exploration explore) {
        this.explore = explore;
    }

    @Override
    public void run() {
        Random rand = new Random();
        int n = explore.getMap().matrix.length;
        int row = rand.nextInt(n);
        int col = rand.nextInt(n);
        int[] nextPos = explore.getMap().getNewPosition(row, col);
        while (running && nextPos != null) {
            if (!paused) {
                row = nextPos[0];
                col = nextPos[1];
                explore.getMap().visit(row, col, this);
                nextPos = explore.getMap().getNewPosition(row, col);
                numTokens++;
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        running = true;
        paused = false;
        new Thread(this).start();
    }

    public void stop() {
        running = false;
    }

    public void pause() {
        paused = true;
    }

    public void pause(long timeInMillis) {
        paused = true;
        pauseTime = timeInMillis;
        new Thread(() -> {
            try {
                Thread.sleep(timeInMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            paused = false;
        }).start();
    }

    public void resume() {
        paused = false;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getTokenCount() {
        return numTokens;
    }
}
