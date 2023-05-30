package org.example;

import java.util.*;

class BestRouteSolution {
    private Map map;

    public BestRouteSolution() {
        map = new Map();
    }

    public void addLocation(Location loc) {
        map.addLocation(loc);
    }

    public void addRoad(Road road) {
        map.addRoad(road);
    }

    public boolean isRoutePossible(Location from, Location to) {
        for (Road road : map.getRoads()) {
            if (road.getFrom().equals(from) && road.getTo().equals(to)) {
                return true;
            }
        }
        return false;
    }

    public List<Location> findBestRoute(Location from, Location to) {
        List<Location> bestRoute = new ArrayList<>();
        Set<Location> visited = new HashSet<>();
        Queue<List<Location>> queue = new LinkedList<>();

        // incep sa verific din "from"
        List<Location> initialPath = new ArrayList<>();
        initialPath.add(from);
        queue.offer(initialPath);

        while (!queue.isEmpty()) {
            List<Location> currentPath = queue.poll();
            Location lastLocation = currentPath.get(currentPath.size() - 1);

            // verific daca am ajuns la desitnatia "to"
            if (lastLocation.equals(to)) {
                return currentPath;
            }

            // verific daca locatia curenta a fost vizitata
            if (visited.contains(lastLocation)) {
                    continue;
            }

            visited.add(lastLocation);

            // explorez totate locatiile posibile din cea curenta
            for (Road road : map.getRoads()) {
                if (road.getFrom().equals(lastLocation)) {
                    // creez un nou drum posibil si l pun in coada
                    List<Location> newPath = new ArrayList<>(currentPath);
                    newPath.add(road.getTo());
                    queue.offer(newPath);
                }
            }
        }

        return bestRoute;
    }
}
