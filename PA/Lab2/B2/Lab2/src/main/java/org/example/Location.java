package org.example;

import java.util.ArrayList;

enum Location_Type {
    cities, airports, gas_stations;
}

class Location {
    private ArrayList locations;
    String name;
    Location_Type type;
    double x, y;

    public Location(String name, Location_Type type, double x, double y) {
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public boolean isValid() {
        if (name == null || name.isEmpty()) {
            return false;
        }

        if (type == null) {
            return false;
        }

        if (x < -90.0 || y > 90.0) {
            return false;
        }

        if (x < -180.0 || y > 180.0) {
            return false;
        }

        return true;
    }

    public void addLocation(Location loc) {
        if (locations.contains(loc)) {
            System.out.println("Location already exists.");
        } else {
            locations.add(loc);
            System.out.println("Location added successfully.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Location)) {
            return false;
        }

        Location loc = (Location) o;

        return this.name.equals(loc.name);
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name2){
        this.name = name2;
    }

    public Location_Type getType() {
        return type;
    }

    public void setType(Location_Type type) {
        this.type = type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}

