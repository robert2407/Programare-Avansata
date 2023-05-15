package org.example;

public class Timer {
    private long startTime;
    private long timeLimit = 60;   //sec.
    private boolean isRunning;

    public void start() {
        startTime = System.currentTimeMillis();
        isRunning = true;
    }

    public long getElapsedSeconds() {
        if (!isRunning) {
            return 0;
        }
        long currentTime = System.currentTimeMillis();
        long elapsedMillis = currentTime - startTime;
        return elapsedMillis / 1000;
    }

    public boolean isTimeUp() {
        return getElapsedSeconds() >= timeLimit;
    }

    public void stop() {
        isRunning = false;
    }
}
