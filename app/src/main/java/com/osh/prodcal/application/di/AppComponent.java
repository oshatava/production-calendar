package com.osh.prodcal.application.di;

import com.osh.prodcal.yearlist.monthcalendar.MonthCalendarFragment;
import com.osh.prodcal.monthinfo.monthdetails.MonthDetailsFragment;
import com.osh.prodcal.monthinfo.monthselector.MonthSelectorFragment;
import com.osh.prodcal.yearlist.YearListFragment;
import com.osh.prodcal.yearlist.yearcalendar.YearCalendarFragment;

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

    void inject(MonthDetailsFragment monthInfoFragment);

    void inject(YearListFragment yearListFragment);

    void inject(YearCalendarFragment yearCalendarFragment);
}
