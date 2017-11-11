package com.osh.prodcal.domain.repository;

import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.domain.dto.Holiday;
import com.osh.prodcal.domain.dto.Month;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface MonthRepository {

    Observable<Month> getMonth(final MonthKeyEntity key);

    Observable<List<Holiday>> getHolidays(int year);

    Observable<List<MonthKeyEntity>> getMonthsIds();

    Observable<List<MonthEntity>> getMonths();

    Observable<List<MonthEntity>> getMonths(final int year);

    Observable<MonthKeyEntity> observeCurrentMonth();

    Observable<MonthKeyEntity> setCurrentMonth(MonthKeyEntity currentMonth);

}
