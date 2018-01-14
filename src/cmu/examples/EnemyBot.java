package cmu.examples;

import robocode.ScannedRobotEvent;

public class EnemyBot {
    String name;


    double bearing;

    double distance;
    double energy;
    double heading;
    double velocity;

    public EnemyBot() {
        reset();
    }

    public String getName() {
        return name;
    }

    public double getBearing() {
        return bearing;
    }

    public void setBearing(double bearing) {
        this.bearing = bearing;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update(ScannedRobotEvent e) {
        setBearing(e.getBearing());
        setDistance(e.getDistance());
        setEnergy(e.getEnergy());
        setHeading(e.getHeading());
        setName(e.getName());
        setVelocity(e.getVelocity());
    }

    public void reset() {
        setName("");
        setVelocity(0.0);
        setHeading(0.0);
        setEnergy(0.0);
        setDistance(0.0);
        setBearing(0.0);
    }

    public boolean none() {
        if(getName().equals(""))
            return true;
        return false;
    }

}
