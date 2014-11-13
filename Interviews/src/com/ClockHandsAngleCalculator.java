package com;

/**
 * Created by dgaglani on 5/23/14.
 */
public class ClockHandsAngleCalculator {

    //time is in 12 hour time
    public int calculateAngleBetweenMinuteHandAndHourHand(int minute, int hour) {
        //minute hand moves 6 degrees a minute upto 360 degrees after which it resets, so minute should be less than 60
        if(minute > 60) {
            minute = minute % 60;
        }

        //hour hand moves .5 degrees a minute(30 degrees an hour), for 360 degrees and resets itself(every half day or 12 hours)
        return 0;
    }
}
