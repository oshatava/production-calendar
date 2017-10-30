package com.osh.prodcal;

import com.osh.prodcal.data.dto.MonthKey;
import com.osh.prodcal.domain.MonthKeyEntity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by olegshatava on 27.10.17.
 */

public class RxTest {
    @Test
    public void Test_getCurrentMonth() {
        List<Integer> items = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        List<MonthKey> months = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            months.add(new MonthKey(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)));
            calendar.add(Calendar.MONTH, 1);
        }


        Observable
                .just(months)
                .flatMapIterable(i -> i)
                .flatMap(m -> Observable.just(m.getYear()))
                .distinct()
                .subscribe(items::add);

        System.out.println(items);

    }

    @Test
    public void Test_getObserveCurrentMonth() {


        Subject<MonthKeyEntity> currentMonth = BehaviorSubject.createDefault(new MonthKeyEntity(0, 0));
        currentMonth.subscribe(new MonthObserver(0));

        currentMonth.onNext(new MonthKeyEntity(1,1));
        currentMonth.onNext(new MonthKeyEntity(1,2));
        currentMonth.onNext(new MonthKeyEntity(1,3));

        currentMonth.subscribe(new MonthObserver(1));

        currentMonth.onNext(new MonthKeyEntity(1,4));

        currentMonth.subscribe(new MonthObserver(2));


    }


    private static class MonthObserver implements Consumer<MonthKeyEntity>{

        private int id;

        public MonthObserver(int id) {
            this.id = id;
        }

        @Override
        public void accept(MonthKeyEntity keyEntity) throws Exception {
            System.out.println("ID:"+id+" "+keyEntity.toString());
        }
    }
}
