package org.example;
class Main {
    public static void main(String[] args) {
        Location l1 = new Location("Iasi", Location_Type.cities, 4, 7);
        Location l2 = new Location("Bucuresti", Location_Type.airports, 10, 14);
        Road r1 = new Road(l1, l2, Road_Type.highways, 7, 60);

        //System.out.println(r1.from.getLength());
        //System.out.println(r1.getType());      0 ???
        Road r2 = new Road(l1, l2, Road_Type.express, 5, 90);
        Road r3 = new Road(l1, l2, Road_Type.country, 12, 100);

        System.out.println(l1.getName() + " " + l1.getType() + " " + l1.getX() + " " + l1.getY() );
        System.out.println(l2.getName() + " " + l2.getType() + " " + l2.getX() + " " + l2.getY() );

        System.out.println(l1.toString());
        l1.setName("Bacau");
        System.out.println(l1);

        System.out.println(r1.getFrom().getName() + " la " + r1.getTo().getName() + " cu " + r1.getType() + " " + r1.getLength() + " " + r1.getSpeedLimit());
        System.out.println(r1);
    }
}

