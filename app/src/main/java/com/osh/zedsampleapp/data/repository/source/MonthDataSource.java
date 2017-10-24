package com.osh.zedsampleapp.data.repository.source;

import com.osh.zedsampleapp.data.dto.Holiday;
import com.osh.zedsampleapp.data.dto.Month;
import com.osh.zedsampleapp.data.dto.MonthKey;
import com.osh.zedsampleapp.domain.repository.MonthRepository;

import java.util.List;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface MonthDataSource {

    Month getMonth(int year, int month);

    List<Holiday> getHolidays(int year);

    List<MonthKey> getMonthIds();
}
