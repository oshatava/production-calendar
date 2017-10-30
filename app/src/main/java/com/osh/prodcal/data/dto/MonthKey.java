package com.osh.prodcal.data.dto;

/**
 * Created by olegshatava on 22.10.17.
 */

public class MonthKey {
    private int year;
    private int month;

    public MonthKey(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }
}
