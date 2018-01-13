package cmu;

import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.awt.*;

public class SecondRobot extends Robot{

    public void run() {
        setColors(Color.pink, Color.pink, Color.pink, Color.pink, Color.pink);

        while (true) {
            ahead(100);
            turnGunRight(360);
            back(100);
            turnGunRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        System.out.println(e.getDistance());
        fire(1.1);

        //adapt fire power according to the distance of the spotted enemy
        //Bullet damage=> 4 * firepower. If firepower > 1, it does an additional damage = 2 * (power - 1).
        //Bullet velocity=> 20 - 3 * firepower.
        //Gun heat generated on firing => 1 + firepower / 5. You cannot fire if gunHeat > 0. All guns are hot at the start of each round.
        //Energy returned on hit => 3 * firepower.
    }
}
