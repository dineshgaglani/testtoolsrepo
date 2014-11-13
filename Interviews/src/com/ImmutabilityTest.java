package com;

import java.awt.*;
import java.util.Date;

/**
 * Created by dgaglani on 5/24/14.
 */
public class ImmutabilityTest {

    public Point weWillAssignThisToPointWeReceive;


    public void getLocation(Point point) {
        //receive a mutable instance here and assign it to a class member variable, change the variable in another method
        weWillAssignThisToPointWeReceive = point;
    }

    public void changeThePoint() {
        weWillAssignThisToPointWeReceive.setLocation(10, 10);
    }

    public static void main(String args[]) {
        ImmutabilityTest it = new ImmutabilityTest();
        Point testPoint = new Point();
        testPoint.setLocation(1, 1);
        it.getLocation(testPoint);
        System.out.print("x: " + testPoint.getX() + ", y: " + testPoint.getY());
        it.changeThePoint();
        System.out.print("x: " + testPoint.getX() + ", y: " + testPoint.getY());
    }
}
