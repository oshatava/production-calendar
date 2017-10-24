package com.osh.zedsampleapp.data.dto;

import java.util.List;

/**
 * Created by olegshatava on 22.10.17.
 */

public class Month {
    private int id;
    private List<Day> days;

    public Month(int id, List<Day> days) {
        this.id = id;
        this.days = days;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
