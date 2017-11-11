package com.osh.prodcal.application.di;

import com.osh.prodcal.monthcalendar.MonthCalendarFragment;
import com.osh.prodcal.monthinfo.MonthInfoFragment;
import com.osh.prodcal.monthselector.MonthSelectorFragment;
import com.osh.prodcal.yearlist.YearListFragment;
import com.osh.prodcal.yearcalendar.YearCalendarFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by oleg on 2/7/2017.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MonthSelectorFragment calendarListFragment);

    void inject(MonthCalendarFragment monthCalendarFragment);

    void inject(MonthInfoFragment monthInfoFragment);

    void inject(YearListFragment yearListFragment);

    void inject(YearCalendarFragment yearCalendarFragment);
}
