package org.example;

public class TimeKeeper implements Runnable {
    private long startTime;
    private long elapsedTime;
    private long timeLimit;
    private Exploration explore;
    private boolean running;

    public TimeKeeper(Exploration explore, long timeLimit) {
        this.startTime = System.currentTimeMillis();
        this.elapsedTime = 0;
        this.timeLimit = timeLimit;
        this.explore = explore;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Exploration time: " + (elapsedTime / 1000) + " seconds");
            if (elapsedTime >= timeLimit) {
                System.out.println("Time limit exceeded, stopping exploration...");
                explore.stopExploration();
                running = false;
            }
        }
    }
}
