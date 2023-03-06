package org.example;
class City extends Location {
    int population;

    public City(String name, double x, double y, int population) {
        super(name, "City", x, y);
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "population=" + population +
                ", name='" + name + '\'' +
                ", locationType='" + locationType + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

class Airport extends Location {

    int numOfTerminals;

    public Airport(String name, double x, double y, int numOfTerminals) {
        super(name, "Airport", x, y);
        this.numOfTerminals = numOfTerminals;
    }

    public int getNumOfTerminals() {
        return numOfTerminals;
    }

    public void setNumOfTerminals(int numOfTerminals) {
        this.numOfTerminals = numOfTerminals;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "numOfTerminals=" + numOfTerminals +
                ", name='" + name + '\'' +
                ", locationType='" + locationType + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

class GasStation extends Location {

    double gasPrice;

    public GasStation(String name, double x, double y, double gasPrice) {
        super(name, "GasStation", x, y);
        this.gasPrice = gasPrice;
    }

    public double getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(double gasPrice) {
        this.gasPrice = gasPrice;
    }

    @Override
    public String toString() {
        return "GasStation{" +
                "gasPrice=" + gasPrice +
                ", name='" + name + '\'' +
                ", locationType='" + locationType + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}




public class Location {

    String name;
    String locationType;
    double x,y;

    public Location(String name, String locationType, double x, double y) {
        this.name = name;
        this.locationType = locationType;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", locationType='" + locationType + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Location)) {
            return false;
        }

        Location loc = (Location) o;
        return this.equals(loc.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String location) {
        this.locationType = location;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}