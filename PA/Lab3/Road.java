package org.example;
class Highway extends Road {
    int lanes;

    public Highway(Location from, Location to, int length, double speedLimit, int lanes) {
        super(from, to, "Highway", length, speedLimit);
        this.lanes = lanes;
    }

    public int getLanes() {
        return lanes;
    }

    public void setLanes(int numLanes) {
        this.lanes = numLanes;
    }

    @Override
    public String toString() {
        return "Highway{" +
                "from=" + getFrom().getName() +
                ", to=" + getTo().getName() +
                ", length=" + this.getLength() +
                ", type=" + getType() +
                ", lanes=" + lanes +
                '}';
    }
}

class Express extends Road {

    int lanes;

    public Express(Location from, Location to, int length, double speedLimit, int lanes) {
        super(from, to,"Express", length, speedLimit);
        this.lanes = lanes;
    }

    public int getLanes() {
        return lanes;
    }

    public void setLanes(int numLanes) {
        this.lanes = numLanes;
    }

    @Override
    public String toString() {
        return "Express{" +
                "from=" + getFrom().getName() +
                ", to=" + getTo().getName() +
                ", length=" + getLength() +
                ", type=" + getType() +
                ", lanes=" + lanes +
                '}';
    }
}
class Country extends Road {

    int lanes;

    public Country(Location from, Location to, int length, double speedLimit, int lanes) {
        super(from, to,"Country", length, speedLimit);
        this.lanes = lanes;
    }

    public double getLanes() {
        return lanes;
    }

    public void setLanes(int lanes) {
        this.lanes = lanes;
    }

    @Override
    public String toString() {
        return "Country{" +
                "from=" + getFrom().getName() +
                ", to=" + getTo().getName() +
                ", length=" + getLength() +
                ", type=" + getType() +
                ", lanes=" + lanes +
                '}';
    }
}

public class Road {
    private Location from, to;
    private String type;
    private double length;
    private double speedLimit;

    public Road(Location from, Location to, String type, double length, double speedLimit) {
        double distanta_XY = Math.sqrt(((from.getX() - to.getX())*(from.getX() - to.getX())) + ((from.getY() - to.getY())*(from.getY() - to.getY())));
        // formula pentru calcularea distantei dintre doua puncte
        if (distanta_XY > length) {
            System.out.println("Drumul de la A " + from.getName() + " la B " + to.getName() +" nu este suficient de lung: " + length + " < " + distanta_XY);
            //return ;   System.exit(0);
        } else {
            System.out.println("Distanta este suficient de mare: " + distanta_XY);
            this.length = length;
        }
        this.from = from;
        this.to = to;
        this.type = type;
        this.speedLimit = speedLimit;
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

    public String getType() {
        return type;
    }

    public double getLength() {
        return length;
    }

    public double getSpeedLimit() {
        return speedLimit;
    }

    public Location getFrom() {
        return from;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public Location getTo() {
        return to;
    }

    public void setTo(Location to) {
        this.to = to;
    }
}