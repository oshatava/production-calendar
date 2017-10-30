package com.osh.prodcal;

import com.osh.prodcal.data.dto.Day;
import com.osh.prodcal.data.dto.Holiday;
import com.osh.prodcal.data.dto.Month;
import com.osh.prodcal.domain.MonthEntity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MonthEntityTest {

    private MonthEntity march2017;
    private static final int YEAR_2017 = 2017;
    private static final int MONTH_MARCH = Calendar.MARCH;
    private static final int MARCH_2017_WORK_DAYS = 22;
    private static final int MARCH_2017_DAYS = 31;
    private static final int MARCH_2017_QUARTER = 1;
    private static final int MARCH_2017_HALF_YEAR = 1;

    private static final int MARCH_2017_NON_WORK_DAYS = 9;
    private static final int MARCH_2017_NUMBER_OF_HOLIDAYS = 1;

    private static final float MARCH_2017_W40_HOURS = 175f;
    private static final float MARCH_2017_W36_HOURS = 157.4f;
    private static final float MARCH_2017_W24_HOURS = 104.6f;


    @Before
    public void TestSetUp() {


        List<Day> days = new ArrayList<>();
        days.add(new Day(7, -1, false, true));
        days.add(new Day(8, 4, false, false));

        List<Holiday> holidays = new ArrayList<>();
        holidays.add(new Holiday(1, "Новогодние каникулы (в ред. Федерального закона от 23.04.2012 № 35-ФЗ)"));
        holidays.add(new Holiday(2, "Рождество Христово"));
        holidays.add(new Holiday(3, "День защитника Отечества"));
        holidays.add(new Holiday(4, "Международный женский день"));
        holidays.add(new Holiday(5, "Праздник Весны и Труда"));
        holidays.add(new Holiday(6, "День Победы"));
        holidays.add(new Holiday(7, "День России"));
        holidays.add(new Holiday(8, "День народного единства"));


        Month month = new Month(MONTH_MARCH, days);
        march2017 = new MonthEntity(YEAR_2017, holidays, month);
    }

    @Test
    public void Test_getCurrentMonth() {
        Assert.assertEquals(march2017.getCurrentMonth().get(Calendar.MONTH), MONTH_MARCH);
    }

    @Test
    public void Test_getNumberOfDay() {
        Assert.assertEquals(march2017.getNumberOfDay(), MARCH_2017_DAYS);
    }

    @Test
    public void Test_getNumberOfWorkingDay() {
        Assert.assertEquals(march2017.getNumberOfWorkingDay(), MARCH_2017_WORK_DAYS);
    }

    @Test
    public void Test_getNumberOfNonWorkingDay() {
        Assert.assertEquals(march2017.getNumberOfNonWorkingDay(), MARCH_2017_NON_WORK_DAYS);
    }

    @Test
    public void Test_getNumberOfHoursForWorkWeek40h() {
        Assert.assertEquals(march2017.getNumberOfHoursForWorkWeek(40), MARCH_2017_W40_HOURS, 0.0001);
    }

    @Test
    public void Test_getNumberOfHoursForWorkWeek36h() {
        Assert.assertEquals(march2017.getNumberOfHoursForWorkWeek(36), MARCH_2017_W36_HOURS, 0.0001);
    }

    @Test
    public void Test_getNumberOfHoursForWorkWeek24h() {
        Assert.assertEquals(march2017.getNumberOfHoursForWorkWeek(24), MARCH_2017_W24_HOURS, 0.0001);
    }

    @Test
    public void Test_getCurrentQuarter() {
        Assert.assertEquals(march2017.getCurrentQuarter(), MARCH_2017_QUARTER);
    }

    @Test
    public void Test_getCurrentHalfYear() {
        Assert.assertEquals(march2017.getCurrentHalfYear(), MARCH_2017_HALF_YEAR);
    }

    @Test
    public void Test_getCurrentYear() {
        Assert.assertEquals(march2017.getCurrentYear(), YEAR_2017);
    }

    @Test
    public void Test_getHolidays(){
        Assert.assertEquals(march2017.getHolidays().size(), MARCH_2017_NUMBER_OF_HOLIDAYS);
    }
}