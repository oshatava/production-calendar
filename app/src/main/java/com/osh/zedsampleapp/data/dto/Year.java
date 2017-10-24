package com.osh.zedsampleapp.data.dto;

import java.util.List;

/**
 * Created by olegshatava on 23.10.17.
 */

public class Year {
    private int id;
    private List<Month> monthData;
    private List<Holiday> holidays;

    public Year(int id, List<Month> monthData, List<Holiday> holidays) {
        this.id = id;
        this.monthData = monthData;
        this.holidays = holidays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Month> getMonthData() {
        return monthData;
    }

    public void setMonthData(List<Month> monthData) {
        this.monthData = monthData;
    }

    public List<Holiday> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<Holiday> holidays) {
        this.holidays = holidays;
    }
}
