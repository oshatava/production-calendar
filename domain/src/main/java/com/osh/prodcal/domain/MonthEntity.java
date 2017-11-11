package com.osh.prodcal.domain;


import com.osh.prodcal.domain.dto.Day;
import com.osh.prodcal.domain.dto.Holiday;
import com.osh.prodcal.domain.dto.Month;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by olegshatava on 22.10.17.
 */

public class MonthEntity {

    private Calendar calendar;
    private final Map<Integer, Holiday> holidays = new HashMap<>();
    private final int monthId;
    private Map<Integer, Day> days = new TreeMap<>();
    private int numberOfWorkingDay = -1;
    private int numberOfFullWorkingDay = -1;
    private int numberOfNonWorkingDay = -1;

    public MonthEntity(int year, List<Holiday> holidays, Month month) {
        this.monthId = month.getId();
        this.calendar = Calendar.getInstance();
        this.calendar.set(Calendar.DAY_OF_MONTH, 1);
        this.calendar.set(Calendar.HOUR_OF_DAY, 0);
        this.calendar.set(Calendar.MINUTE, 0);
        this.calendar.set(Calendar.YEAR, year);
        this.calendar.set(Calendar.MONTH, monthId);
        initDays(holidays, month.getDays());
    }

    private void initDays(List<Holiday> holidays, List<Day> days) {
        Map<Integer, Holiday> tempHolidays = new HashMap<>();
        for(Holiday holiday:holidays) {
            tempHolidays.put(holiday.getId(), holiday);
        }

        // Find out all holidays in the month
        for(Day d:days) {
            this.days.put(d.getId(), d);
            if (tempHolidays.containsKey(d.getHoliday())) {
                Holiday holiday = tempHolidays.get(d.getHoliday());
                this.holidays.put(holiday.getId(), holiday);
            }
        }


        // Fill gaps in month days
        Calendar month = getCurrentMonth();
        for (int i = 1; i <= month.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            month.set(Calendar.DAY_OF_MONTH, i);
            boolean isWeekEnd = month.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || month.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
            if(!this.days.containsKey(i))
                this.days.put(i, new Day(i, -1, !isWeekEnd, !isWeekEnd));
        }

        numberOfWorkingDay = getNumberOfWorkingDay(false);
        numberOfNonWorkingDay = getNumberOfDay() - numberOfWorkingDay;
        numberOfFullWorkingDay = getNumberOfWorkingDay(true);
    }

    public Calendar getCurrentMonth() {
        Calendar ret = new GregorianCalendar();
        ret.setTimeInMillis(calendar.getTimeInMillis());
        return ret;
    }

    public int getMonthId() {
        return monthId;
    }

    public int getNumberOfDay() {
        return days.size();
    }

    public int getNumberOfWorkingDay() {
        return numberOfWorkingDay;
    }

    public int getNumberOfNonWorkingDay() {
        return numberOfNonWorkingDay;
    }

    public float getNumberOfHoursForWorkWeek(int maxHoursPerWeek) {
        int notFullWorkDays = numberOfWorkingDay - numberOfFullWorkingDay;
        return (maxHoursPerWeek / 5f) * numberOfWorkingDay - notFullWorkDays;
    }

    public int getCurrentQuarter() {
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }

    public int getCurrentHalfYear() {
        return calendar.get(Calendar.MONTH) / 6 + 1;
    }

    public int getCurrentYear() {
        return calendar.get(Calendar.YEAR);
    }

    public List<Holiday> getHolidays(){
        List<Holiday> ret = new ArrayList<>();
        ret.addAll(this.holidays.values());
        return ret;
    }

    private int getNumberOfWorkingDay(boolean excludeNonFull) {
        int ret = 0;
        for (Day day: days.values()) {
            if(!day.isWorked())
                continue;
            if(excludeNonFull && (day.isWorked()&& !day.isFull()))
                continue;
            ret++;
        }

        return ret;
    }

    public Map<Integer, Day> getDays() {
        return days;
    }
}
