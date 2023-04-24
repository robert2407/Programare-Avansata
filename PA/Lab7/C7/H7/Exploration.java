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
    private boolean running;

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

    public void pauseRobot(Robot robot) {
        robot.pause();
    }

    public void pauseRobot(Robot robot, long pauseTime) {
        robot.pause(pauseTime);
    }

    public void pauseAllRobots() {
        for (Robot robot : robots) {
            robot.pause();
        }
    }

    public void pauseAllRobots(long pauseTime) {
        for (Robot robot : robots) {
            robot.pause(pauseTime);
        }
    }

    public void resumeRobot(Robot robot) {
        robot.resume();
    }

    public void resumeAllRobots() {
        for (Robot robot : robots) {
            robot.resume();
        }
    }

    public ExplorationMap getMap() {
        return map;
    }



    public void startExploration(long timeLimit) {
        running = true;
        TimeKeeper timeKeeper = new TimeKeeper(this, timeLimit);
        Thread timeKeeperThread = new Thread(timeKeeper);
        timeKeeperThread.setDaemon(true);
        timeKeeperThread.start();
        for (Robot robot : robots) {
            robot.start();
        }
    }

    public void stopExploration() {
        running = false;
        for (Robot robot : robots) {
            robot.stop();
        }
    }

    public boolean isRunning() {
        return running;
    }

    public static void main(String args[]) {
        var explore = new Exploration();
        explore.addRobot(new Robot("Wall-E"));
        explore.addRobot(new Robot("R2D2"));
        explore.addRobot(new Robot("Optimus Prime"));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter command (s = start, b = stop, p = pause, r = resume): ");
            String input = scanner.nextLine().toLowerCase();
            long timeLimit = 5000;
            if (input.equals("s")) {
                //explore.start();
                explore.startExploration(timeLimit);
                try {
                    Thread.sleep(timeLimit);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                explore.stopExploration();

                for (Robot robot : explore.robots) {
                    int tokenCount = robot.getTokenCount();
                    System.out.println(robot.getName() + " are " + tokenCount + " tokeni in matrice.");
                }

                break;
            } else if (input.equals("b")) {
                explore.stop();
            } else if (input.equals("p")) {
//                explorep.stop();
                System.out.print("Robotul numarul ... pentru pauza (1-" + explore.robots.size() + "): ");
                int robotNumber = scanner.nextInt() - 1;
                scanner.nextLine();
                explore.pauseRobot(explore.robots.get(robotNumber));
//                explore.start();
            } else if (input.equals("r")) {
//                explore.stop();
                System.out.print("Robotul numarul ... pentru reintrare in joc (1-" + explore.robots.size() + "): ");
                int robotNumber = scanner.nextInt() - 1;
                scanner.nextLine();
                explore.resumeRobot(explore.robots.get(robotNumber));
//                explore.start();
            }
        }
    }

}
