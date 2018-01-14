package cmu;

import cmu.examples.EnemyBot;
import robocode.*;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

//better for multiplayer
public class WIPRobot extends AdvancedRobot {
    private byte scanDirection = 1;
    private EnemyBot enemy = new EnemyBot();

    public void run() {

        setAllColors(Color.magenta);
//        setAdjustGunForRobotTurn(true);
        setAdjustRadarForRobotTurn(true);

        enemy.reset();

        while (true) {
//            if(getOthers() > 1) {
//                setAhead(100);
//                setTurnRadarRight(360);
//                setBack(100);
//                setTurnRadarRight(360);
//            } else {
//                // Turn the scanner until we find an enemy robot
//                setTurnRadarRight(36000);
//            }


            doScanner();
            doMovement();
            smartFire();
            execute();

        }
    }
    void doScanner() {
        setTurnRadarRight(360);
    }

    void doMovement() {
        // turning here causes a weird behavior, prolly because we're working
        // with outdated information
        //setTurnRight(enemy.getBearing());

        // move a little closer
        if (enemy.getDistance() > 200)
            setAhead(enemy.getDistance() / 2);
        // but not too close
        if (enemy.getDistance() < 100)
            setBack(enemy.getDistance());
    }
    public void onScannedRobot(ScannedRobotEvent e) {

        // check if we have no enemy or found the one we were tracking
        if (enemy.none() || e.getName().equals(enemy.getName())) {
            enemy.update(e);

            // When we scan a robot, turn toward him
            turnRight(e.getBearing());

            // move a little closer
            if (e.getDistance() > 200)
                setAhead(e.getDistance() / 2);
            // but not too close
            if (e.getDistance() < 100)
                setBack(e.getDistance());

            if(getOthers() > 1) { //if more than one opponent

                // shoot at him
                smartFire();

                // wobble the radar to generate another scan event
                scanDirection *= -1;
                setTurnRadarRight(36000 * scanDirection);

            } else {

                smartFire();

                // Lock on to our target (this time for sure) - useful in 1on1 mode
                setTurnRadarRight(getHeading() - getRadarHeading() + e.getBearing());
            }
        }
    }


    private void smartFire() {

        // don't fire if there's no enemy
        if (enemy.none()) return;

        // convenience variable
        double max = Math.max(getBattleFieldHeight(), getBattleFieldWidth());

        // only shoot if we're (close to) pointing at our enemy
        if (Math.abs(getTurnRemaining()) < 10) {
            if (enemy.getDistance() < max / 3) {
                // fire hard when close
                setFire(3);
            } else {
                // otherwise, just plink him
                setFire(1.1);
            }
        }
    }

    private void ramFire(double energy) {
        setFire(3);

        doRam();

    }


    private void doRam() {
        setBack(50);
        setAhead(100);
    }


    public void onHitByBullet(HitByBulletEvent e) {
        setTurnRight(90);
        setBack(100);
        setTurnLeft(90);
    }


    public void onHitRobot(HitRobotEvent event) {
        System.out.println("will ram");
        if ((getEnergy() > event.getEnergy() && getOthers() < 2) ) //if my energy is bigger than the only opponent
            ramFire(event.getEnergy());
        else if (event.getBearing() > -90 && event.getBearing() <= 90) { //if it's in front of me

            smartFire();
            setBack(100);
        } else {
            System.out.println("on hit bearing back " + event.getBearing());
            smartFire();
            setAhead(100);

        }
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

    public void onRobotDeath(RobotDeathEvent e) {
        // check if the enemy we were tracking died
        if (e.getName().equals(enemy.getName())) {
            enemy.reset();
        }
    }

    public void onWin(WinEvent e) {
        setTurnRight(90);
        setTurnGunLeft(90);
        setTurnRight(90);
        setTurnGunLeft(90);
        setTurnRight(90);
        setTurnGunLeft(90);
        setTurnRight(90);
        setTurnGunLeft(90);

        setTurnRight(90);
        setTurnGunLeft(90);
        setTurnRight(90);
        setTurnGunLeft(90);
        setTurnRight(90);
        setTurnGunLeft(90);
        setTurnRight(90);
        setTurnGunLeft(90);

        setAllColors(Color.cyan);
        setAllColors(Color.green);
        setAllColors(Color.red);
        setAllColors(Color.blue);
        setAllColors(Color.magenta);

    }

    public static int randInt(int min, int max) {

        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        return randomNum;
    }



}
