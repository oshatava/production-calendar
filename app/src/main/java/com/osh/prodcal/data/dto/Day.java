package com.osh.prodcal.data.dto;

/**
 * Created by olegshatava on 23.10.17.
 */

public class Day {
    private int id;
    private int holiday;
    private boolean isFull;
    private boolean isWorked;

    public Day(int id, int holiday, boolean isFull, boolean isWorked) {
        this.id = id;
        this.holiday = holiday;
        this.isFull = isFull;
        this.isWorked = isWorked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHoliday() {
        return holiday;
    }

    public void setHoliday(int holiday) {
        this.holiday = holiday;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public boolean isWorked() {
        return isWorked;
    }

    public void setWorked(boolean worked) {
        isWorked = worked;
    }
}
