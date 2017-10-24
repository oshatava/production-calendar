package com.osh.zedsampleapp.data.repository;

import com.osh.zedsampleapp.data.dto.MonthKey;
import com.osh.zedsampleapp.data.repository.source.MonthDataSource;
import com.osh.zedsampleapp.data.dto.Holiday;
import com.osh.zedsampleapp.data.dto.Month;
import com.osh.zedsampleapp.domain.MonthKeyEntity;
import com.osh.zedsampleapp.domain.repository.MonthRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by olegshatava on 23.10.17.
 */

public class MonthRepositoryImpl implements MonthRepository{

    private MonthDataSource monthDataSource;

    public MonthRepositoryImpl(MonthDataSource monthDataSource) {
        this.monthDataSource = monthDataSource;
    }

    @Override
    public Observable<Month> getMonth(final MonthKeyEntity key) {
        return Observable.create(new ObservableOnSubscribe<Month>() {
            @Override
            public void subscribe(ObservableEmitter<Month> e) throws Exception {
                try{
                    e.onNext(monthDataSource.getMonth(key.getYear(), key.getMonth()));
                }catch (Exception ex){
                    e.onError(ex);
                }
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<List<Holiday>> getHolidays(final int year) {
        return Observable.create(new ObservableOnSubscribe<List<Holiday>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Holiday>> e) throws Exception {
                try{
                    e.onNext(monthDataSource.getHolidays(year));
                }catch (Exception ex){
                    e.onError(ex);
                }
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<List<MonthKeyEntity>> getMonthsIds() {
        return Observable.create(new ObservableOnSubscribe<List<MonthKeyEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<MonthKeyEntity>> e) throws Exception {
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
            }
        });
    }


}
