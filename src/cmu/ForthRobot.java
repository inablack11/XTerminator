package cmu;

import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.awt.*;

public class ForthRobot extends Robot{
    public void run() {
        setAllColors(Color.green);

        while (true) {
            ahead(100);
            turnGunRight(360);
            back(100);
            turnGunRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        System.out.println(e.getDistance());

        if (e.getDistance()<200) {
            if(getOthers()<2) //are sens?
                fire(3);
            else
                fire(2);
        } else
            fire(1.1);

        if(getEnergy()>20) {
            //decide fire power
        }
    }

    public void onHitByBullet(HitByBulletEvent e) {
        turnRight(45);
        back(100);
    }

    public void onHitWall(HitWallEvent e) {
//        turnRight(180);
//        ahead(100);
    }
}
