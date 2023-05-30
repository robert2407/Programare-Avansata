package org.example;
import java.util.*;
class Main {
    public static void main(String[] args) {
        BestRouteSolution solution = new BestRouteSolution();

        Location l1 = new Location("Iasi", Location_Type.cities, 4, 7);
        Location l2 = new Location("Bucuresti", Location_Type.airports, 10, 14);
        Location l3 = new Location("Arad", Location_Type.cities, 5, 7);

        Road r1 = new Road(l1, l2, Road_Type.highways, 20, 60);
        Road r2 = new Road(l2, l3, Road_Type.express, 55, 90);
        Road r3 = new Road(l1, l3, Road_Type.country, 12, 100);

        solution.addLocation(l1);
        solution.addLocation(l2);

        solution.addRoad(r1);
        solution.addRoad(r2);

        List<Location> bestRoute = solution.findBestRoute(l1, l3);
        if (!bestRoute.isEmpty()) {
            System.out.println("Best route from " + l1.getName() + " to " + l2.getName() + ":");
            for (Location location : bestRoute) {
                System.out.println(location.getName());
            }
        } else {
            System.out.println("No route found from " + l1.getName() + " to " + l2.getName());
        }


        Map map = new Map();
        map.addLocation(l1);
        map.addLocation(l2);
        map.addRoad(r1);
        map.addRoad(r2);
        map.addRoad(r3);



        // verificare ca exista drum de la a la b
        boolean pathExists;
        if(r1.getFrom().getName() == l1.getName() && r1.getTo().getName() == l2.getName())
             pathExists = true;
        else  pathExists = false;
        if (pathExists) {
            System.out.println("Este drum de la " + l1.getName() + " pana la " + l2.getName());
        } else {
            System.out.println("Nu este drum de la " + l1.getName() + " pana la " + l2.getName());
        }

        map.addLocation(l2);
        if (l1.equals(l3)) {
            System.out.println("Locatia este deja in sistem");
        } else
        System.out.println(l1.equals(l1));

        double time = r1.getLength() / r1.getSpeedLimit();
        System.out.println("Dureaza " + time + " ore de la " + l1.getName() + " pana " + l2.getName() );

        System.out.println(l1.getName() + " " + l1.getType() + " " + l1.getX() + " " + l1.getY() );
        System.out.println(l2.getName() + " " + l2.getType() + " " + l2.getX() + " " + l2.getY() );

        System.out.println(l1.toString());
        l1.setName("Bacau");
        System.out.println(l1);

        System.out.println(r1.getFrom().getName() + " la " + r1.getTo().getName() + " cu " + r1.getType() + " " + r1.getLength() + " " + r1.getSpeedLimit());
        System.out.println(r1);
    }
}

