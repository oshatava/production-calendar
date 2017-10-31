package com.osh.prodcal.data;

import com.osh.prodcal.data.dto.Day;
import com.osh.prodcal.data.dto.Holiday;
import com.osh.prodcal.data.dto.Month;
import com.osh.prodcal.data.dto.MonthKey;
import com.osh.prodcal.data.repository.MonthRepositoryImpl;
import com.osh.prodcal.data.repository.source.MonthDataSource;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.domain.repository.MonthRepository;
import com.osh.prodcal.presentation.views.utils.CollectionUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by olegshatava on 27.10.17.
 */

public class MonthRepositoryTest {

    MonthRepository repository;
    private MonthKeyEntity defaultMonth;
    private Map<MonthKeyEntity, Month> months;
    private TreeMap<Integer, List<Holiday>> holidays;

    @Before
    public void setUp(){
        Calendar calendar = Calendar.getInstance();
        defaultMonth = new MonthKeyEntity(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));

        // Init months
        months = new TreeMap<>();
        for(int i=2016; i<2018; i++)
            for(int m=0; m<12; m++)
                months.put(new MonthKeyEntity(i, m), new Month(m, new ArrayList<>()));

        // Init holidays
        holidays = new TreeMap<>();
        holidays.put(2017, CollectionUtils.list(new Holiday(1, "New Year")));

        // Create repo
        repository = new MonthRepositoryImpl(createMockDataSource());
    }

    private MonthDataSource createMockDataSource() {
        return new MonthDataSource() {

            @Override
            public Month getMonth(int year, int month) {
                MonthKeyEntity key = new MonthKeyEntity(year, month);
                if(months.containsKey(key))
                    return months.get(key);
                return new Month(month, new ArrayList<>());
            }

            @Override
            public List<Holiday> getHolidays(int year) {
                if(holidays.containsKey(year))
                    return holidays.get(year);
                return new ArrayList<>();
            }

            @Override
            public List<MonthKey> getMonthIds() {
                return CollectionUtils.map(months.keySet(), i -> new MonthKey(i.getYear(), i.getMonth()));
            }
        };
    }

    protected  <T> Observable<T> prepareObservable(Observable<T> observable){
        // Prepare observable
        // Set up Schedulers and so on
        return observable;
    }

    @Test
    public void Test_observeCurrentMonthDefault() {
        prepareObservable(repository.observeCurrentMonth())
                .subscribe(monthKeyEntity -> {
                    Assert.assertEquals(defaultMonth, monthKeyEntity);
                });
    }

    @Test
    public void Test_setCurrentMonthDefault() {
        final MonthKeyEntity key = new MonthKeyEntity(0, 0);

        prepareObservable(repository.setCurrentMonth(key))
                .subscribe(monthKeyEntity -> {
                    Assert.assertEquals(key, monthKeyEntity);
                });
    }

    @Test
    public void Test_observeCurrentMonth() {
        final MonthKeyEntity key = new MonthKeyEntity(0, 0);

        prepareObservable(repository.setCurrentMonth(key))
                .subscribe(monthKeyEntity -> {
                    Assert.assertEquals(key, monthKeyEntity);
                });

        prepareObservable(repository.observeCurrentMonth())
                .subscribe(monthKeyEntity -> {
                    Assert.assertEquals(key, monthKeyEntity);
                });
    }

}
