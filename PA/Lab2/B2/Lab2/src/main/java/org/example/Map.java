package org.example;

import java.util.ArrayList;
import java.util.List;

class Map {
    private List<Location> locations;
    private List<Road> roads;

    public Map() {
        locations = new ArrayList<>();
        roads = new ArrayList<>();
    }

    public void addLocation(Location loc) {
        if (locations.contains(loc)) {
            System.out.println("Location already exists.");
        } else {
            locations.add(loc);
            System.out.println("Location added successfully.");
        }
    }

    public void addRoad(Road road) {
        if (roads.contains(road)) {
            System.out.println("Road already exists.");
        } else {
            roads.add(road);
            System.out.println("Road added successfully.");
        }
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<Road> getRoads() {
        return roads;
    }
}
