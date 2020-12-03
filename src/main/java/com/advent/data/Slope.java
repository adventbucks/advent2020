package com.advent.data;

public class Slope {

    private int rise;
    private int run;

    public Slope(int rise, int run) {
        this.rise = rise;
        this.run = run;
    }

    public int getRise() {
        return rise;
    }

    public void setRise(int rise) {
        this.rise = rise;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }
}