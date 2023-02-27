package org.example;


enum Road_Type {
    highways, express, country;
}

class Road {
    Location from, to;
    Road_Type type;
    double length, speed_Limit;

    public Road(Location from, Location to, Road_Type type, double length, double speed_Limit) {
        double distanta_XY = Math.sqrt(((from.getX() - to.getX())*(from.getX() - to.getX())) + ((from.getY() - to.getY())*(from.getY() - to.getY())));

        // length < distanta_XY exceptie
        if(distanta_XY > length){
            System.out.println("Drumul de la A la B nu este suficient de lung: " + length + " < " + distanta_XY);
            //return ;
        }else{
            System.out.println("Distanta este suficient de mare: " + distanta_XY);
        }

        this.from = from;
        this.to = to;
        this.type = type;
        this.length = length;
        this.speed_Limit = speed_Limit;
    }

    @Override
    public String toString() {
        return "Road{" +
                "from=" + from +
                ", to=" + to +
                ", type=" + type +
                ", length=" + length +
                ", speed_Limit=" + speed_Limit +
                '}';
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public Road_Type getType() {
        return type;
    }

    public double getLength() {
        return length;
    }

    public double getSpeedLimit() {
        return speed_Limit;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public void setTo(Location to) {
        this.to = to;
    }

    public void setType(Road_Type type) {
        this.type = type;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setSpeed_Limit(double speed_Limit) {
        this.speed_Limit = speed_Limit;
    }
}

