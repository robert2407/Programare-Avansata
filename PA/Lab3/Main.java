package org.example;
import java.util.*;
class Main {
    public static void main(String[] args) {
        Location l1 = new Location("Iasi", "City", 4, 7);
        Location l2 = new Location("Bucuresti", "Airport", 10, 14);
        Location l3 = new Location("Iasi", "City", 4, 7);

        Road r1 = new Road(l1, l2, "Highway", 500, 60);
        Road r2 = new Road(l2, l3, "Express", 5, 90);
        Road r3 = new Road(l1, l3, "Country", 12, 100);

        Map map = new Map();
        map.addLocation(l1);
        map.addLocation(l2);
        map.addRoad(r1);
        map.addRoad(r2);
        map.addRoad(r3);
        //map.addRoad(r3);  // Drumul exista deja

        if (l1.equals(l3)) {
            System.out.println("Locatia este deja in sistem");
        } else
            System.out.println(l1.equals(l1));      //true

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

        double time = r1.getLength() / r1.getSpeedLimit();
        System.out.println("Dureaza " + time + " ore de la " + l1.getName() + " pana " + l2.getName() );

        System.out.println(l1.getName() + " " + l1.locationType + " " + l1.getX() + " " + l1.getY() );
        System.out.println(l2.getName() + " " + l2.locationType + " " + l2.getX() + " " + l2.getY() );

        System.out.println(l1.toString());
        l1.setName("Bacau");
        l1.setLocationType("Airport");
        System.out.println(l1);
    }
}

