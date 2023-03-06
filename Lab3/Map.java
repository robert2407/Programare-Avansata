package org.example;

import java.util.*;

class Map {
    ArrayList locations;
    ArrayList roads;

    public Map() {
        locations = new ArrayList();
        roads = new ArrayList();
    }

    public void addLocation(Location loc) {
        if (locations.contains(loc)) {
            System.out.println("Locatia exista deja");
        } else {
            locations.add(loc);
            System.out.println("Locatia s-a adaugat");
        }
    }


    public void addRoad(Road road) {
        if (roads.contains(road)) {
            System.out.println("Drumul exista deja");
        } else {
            roads.add(road);
            System.out.println("Drumul s-a adaugat");
        }
    }
}