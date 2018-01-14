package cmu;

import robocode.*;
import robocode.Robot;

import java.awt.*;

public class ThirdRoobot extends Robot{
    public void run() {
        setColors(Color.cyan, Color.cyan, Color.cyan, Color.cyan, Color.cyan);
        while (true) {
            ahead(100);
            turnGunRight(360);
            back(100);
            turnGunRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        System.out.println(e.getDistance());

        if (e.getDistance()<200)
            fire(3);
        else
            fire(1.1);

    }

    public void onHitByBullet(HitByBulletEvent e) {
        turnRight(45);
        back(100);

    }

}
