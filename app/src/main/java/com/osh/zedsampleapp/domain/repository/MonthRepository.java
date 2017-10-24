package com.osh.zedsampleapp.domain.repository;

import android.os.Parcel;
import android.os.Parcelable;

import com.osh.zedsampleapp.data.dto.Holiday;
import com.osh.zedsampleapp.data.dto.Month;
import com.osh.zedsampleapp.domain.MonthKeyEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface MonthRepository {

    Observable<Month> getMonth(final MonthKeyEntity key);

    Observable<List<Holiday>> getHolidays(int year);

    Observable<List<MonthKeyEntity>> getMonthsIds();


}
