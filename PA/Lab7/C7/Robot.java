package org.example;

import org.example.Exploration;

import java.util.Random;

public class Robot implements Runnable {
    public String name;
    public boolean running;
    public Exploration explore;

    public Robot(String name) {
        this.name = name;
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
        while (running) {
            int[] newPos = explore.getMap().getNewPosition(row, col);
            if (newPos == null) {
                break;  //nu mai are vecini nevizitati
            }
            row = newPos[0];
            col = newPos[1];
            explore.getMap().visit(row, col, this);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void stop() {
        running = false;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
