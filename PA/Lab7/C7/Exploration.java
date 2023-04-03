package org.example;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.example.ExplorationMap;

public class Exploration {
    private final SharedMemory mem = new SharedMemory(10);
    private final ExplorationMap map = new ExplorationMap(10);
    private final List<Robot> robots = new ArrayList<>();

    public void addRobot(Robot robot) {
        robot.setExplore(this);
        robots.add(robot);
    }

    public void start() {
        for (Robot robot : robots) {
            robot.start();
        }
    }

    public void stop() {
        for (Robot robot : robots) {
            robot.stop();
        }
    }

    public ExplorationMap getMap() {
        return map;
    }

    public static void main(String args[]) {
        var explore = new Exploration();
        explore.addRobot(new Robot("Wall-E"));
        explore.addRobot(new Robot("R2D2"));
        explore.addRobot(new Robot("Optimus Prime"));
//        Runnable task = explore.start();
//        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
//        executor.schedule(task, 5, TimeUnit.SECONDS);
        explore.start();
//        explore.stop();
    }
}
