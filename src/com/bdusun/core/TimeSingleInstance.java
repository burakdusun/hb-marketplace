package com.bdusun.core;


public class TimeSingleInstance {

    private int time;

    public TimeSingleInstance() {
        time = 0;
    }

    public void increaseTime(int elapsedTime) {
        time = (time + elapsedTime) % 24;
        getCurrentTime();
    }

    private String timeToString() {
        if (time < 10) {
            return "0" + time + ":" + "00";
        } else {
            return time + ":" + "00";
        }
    }

    public void getCurrentTime() {
        System.out.println("Time is " + timeToString());
    }

}
