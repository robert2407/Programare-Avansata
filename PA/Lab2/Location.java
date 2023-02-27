package org.example;

import org.w3c.dom.ls.LSOutput;

enum Location_Type {
    cities, airports, gas_stations;
}

class Location {
    String name;
    Location_Type type;
    double x, y;

    public Location(String name, Location_Type type, double x, double y) {
        this.name = name;
        this.type = type;
        this.x = x;
        this.y = y;
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

    public Location_Type getType() {
        return type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setName(String name2){
        this.name = name2;
    }

    public void setType(Location_Type type) {
        this.type = type;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}

