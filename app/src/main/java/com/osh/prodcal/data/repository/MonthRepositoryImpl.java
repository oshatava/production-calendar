package com.osh.prodcal.data.repository;

import com.osh.prodcal.data.dto.Holiday;
import com.osh.prodcal.data.dto.Month;
import com.osh.prodcal.data.dto.MonthKey;
import com.osh.prodcal.data.repository.source.MonthDataSource;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.domain.repository.MonthRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthRepositoryImpl implements MonthRepository{

    private MonthDataSource monthDataSource;
    private Subject<MonthKeyEntity> currentMonthSubject;

    public MonthRepositoryImpl(MonthDataSource monthDataSource) {
        this.monthDataSource = monthDataSource;
    }

    @Override
    public Observable<Month> getMonth(final MonthKeyEntity key) {
        return Observable.create(e -> {
            try{
                e.onNext(monthDataSource.getMonth(key.getYear(), key.getMonth()));
            }catch (Exception ex){
                e.onError(ex);
            }
            e.onComplete();
        });
    }

    @Override
    public Observable<List<Holiday>> getHolidays(final int year) {
        return Observable.create(e -> {
            try{
                e.onNext(monthDataSource.getHolidays(year));
            }catch (Exception ex){
                e.onError(ex);
            }
            e.onComplete();
        });
    }

    @Override
    public Observable<List<MonthKeyEntity>> getMonthsIds() {
        return Observable.create(e -> {
            try{

                List<MonthKeyEntity> ret = new ArrayList<MonthKeyEntity>();
                for(MonthKey key:monthDataSource.getMonthIds()){
                    ret.add(new MonthKeyEntity(key.getYear(), key.getMonth()));
                }

                e.onNext(ret);
            }catch (Exception ex){
                e.onError(ex);
            }
            e.onComplete();
        });
    }

    @Override
    public Observable<List<MonthEntity>> getMonths() {
        return Observable.create(e -> {
            try{
                List<MonthEntity> ret = new ArrayList<>();
                for(MonthKey key:monthDataSource.getMonthIds()){
                    MonthEntity monthEntity = new MonthEntity(key.getYear(),
                            monthDataSource.getHolidays(key.getYear()),
                            monthDataSource.getMonth(key.getYear(), key.getMonth()));
                    ret.add(monthEntity);
                }
                e.onNext(ret);
            }catch (Exception ex){
                e.onError(ex);
            }
            e.onComplete();
        });
    }

    @Override
    public Observable<List<MonthEntity>> getMonths(final int year) {
        return Observable.create(e -> {
            try{
                List<MonthEntity> ret = new ArrayList<>();
                for(MonthKey key:monthDataSource.getMonthIds()){
                    if(key.getYear()==year) {
                        MonthEntity monthEntity = new MonthEntity(key.getYear(),
                                monthDataSource.getHolidays(key.getYear()),
                                monthDataSource.getMonth(key.getYear(), key.getMonth()));
                        ret.add(monthEntity);
                    }
                }
                e.onNext(ret);
            }catch (Exception ex){
                e.onError(ex);
            }
            e.onComplete();
        });
    }


    public Subject<MonthKeyEntity> getCurrentMonthSubject() {
        if(currentMonthSubject==null){
            Calendar calendar = Calendar.getInstance();
            currentMonthSubject = BehaviorSubject.createDefault(new MonthKeyEntity(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)));
        }

        return currentMonthSubject;
    }

    @Override
    public Observable<MonthKeyEntity> setCurrentMonth(MonthKeyEntity currentMonth) {
        return Observable.create(e -> {
            try{
                getCurrentMonthSubject().onNext(currentMonth);
                e.onNext(currentMonth);
            }catch (Exception ex){
                e.onError(ex);
            }
            e.onComplete();
        });
    }

    @Override
    public Observable<MonthKeyEntity> observeCurrentMonth() {
        return getCurrentMonthSubject();
    }

}
