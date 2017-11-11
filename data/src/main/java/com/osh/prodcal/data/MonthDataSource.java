package com.osh.prodcal.data;

import com.osh.prodcal.domain.dto.Holiday;
import com.osh.prodcal.domain.dto.Month;
import com.osh.prodcal.domain.dto.MonthKey;

import java.util.List;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface MonthDataSource {

    Month getMonth(int year, int month);

    List<Holiday> getHolidays(int year);

    List<MonthKey> getMonthIds();
}
