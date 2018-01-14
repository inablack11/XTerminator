package cmu;

import robocode.*;
import robocode.Robot;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//better for multiplayer
public class ForthRobot extends AdvancedRobot {
    public void run() {
        setColors(Color.black, Color.yellow, Color.gray, Color.lightGray, Color.orange);
//        setAdjustGunForRobotTurn(true);
        setAdjustRadarForRobotTurn(true);
        while (true) {
//            turnRadarRight(10000);
            ahead(100);
            turnRadarRight(360);
            back(100);
            turnRadarRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        System.out.println("robot was scanned. turning gun " + (getHeading() - getGunHeading() + e.getBearing()));
        System.out.println("getHeading " + getHeading() +
                "\ngetGunHeading " + getGunHeading() +
                "\ne.getBearing " + e.getBearing());
        turnRight(e.getBearing());
        smartFire(e.getDistance());

    }


    private void smartFire(double distance) {
        System.out.println("firing");
        if (distance < 200 && getEnergy() > 20) {
            fire(3);
        } else
            fire(1.1);
    }

    private void ramFire(double energy) {
        if(energy > 16)
            fire(3);
        else if (energy > 10)
            fire(2);
        else if (energy > 4)
            fire(1);
        else if (energy > 2)
            fire(0.5);
        else if (energy > 0.4)
            fire(0.1);

        doRam();

    }

    public void onHitByBullet(HitByBulletEvent e) {
        turnRight(90);
        back(100);
    }


    public void onHitRobot(HitRobotEvent event) {
        System.out.println("will ram");
        if ((getEnergy() > event.getEnergy() && getOthers() < 2) ) //if my energy is bigger than the only opponent
            ramFire(event.getEnergy());
        else if (event.getBearing() > -90 && event.getBearing() <= 90) { //if it's in front of me

            smartFire(0);
            back(100);
        } else {
            System.out.println("on hit bearing back " + event.getBearing());
            smartFire(0);
            ahead(100);

        }
    }

    private void doRam() {
        System.out.println("energy before=" + getEnergy());
        back(50);
        ahead(100);
        System.out.println("energy after=" + getEnergy());
    }

//    public void onHitWall(HitWallEvent e) {
//        int turnDegree = randInt(91, 179);
//        System.out.println("hit wall, turning right " + turnDegree
//                + " \ndegrees. heading is: " + getHeading()
//                + " \nbearing to wall " + e.getBearing());
////        heading - absolute angle in degrees with 0 facing up the screen, positive clockwise. 0 <= heading < 360.
////        bearing - relative angle to some object from your robot's heading, positive clockwise. -180 < bearing <= 180
//
//        turnRight(turnDegree);
//        back(100);
//    }

    public void onWin(WinEvent e) {
//        turnRight(90);
//        turnGunLeft(90);
//        turnRight(90);
//        turnGunLeft(90);
//        turnRight(90);
//        turnGunLeft(90);
//        turnRight(90);
//        turnGunLeft(90);
//
//        turnRight(90);
//        turnGunLeft(90);
//        turnRight(90);
//        turnGunLeft(90);
//        turnRight(90);
//        turnGunLeft(90);
//        turnRight(90);
//        turnGunLeft(90);
//
//        setAllColors(Color.cyan);
//        setAllColors(Color.green);
//        setAllColors(Color.red);
//        setAllColors(Color.blue);
//        setAllColors(Color.magenta);

    }

    public static int randInt(int min, int max) {

        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        return randomNum;
    }
}
