package com.osh.prodcal.data.repository.source;

import com.osh.prodcal.data.dto.Holiday;
import com.osh.prodcal.data.dto.Month;
import com.osh.prodcal.data.dto.MonthKey;

import java.util.List;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface MonthDataSource {

    Month getMonth(int year, int month);

    List<Holiday> getHolidays(int year);

    List<MonthKey> getMonthIds();
}
