package com.osh.prodcal.application.di;

import com.osh.prodcal.presentation.fragments.month.MonthCalendarFragment;
import com.osh.prodcal.presentation.fragments.month.MonthInfoFragment;
import com.osh.prodcal.presentation.fragments.month.MonthSelectorFragment;
import com.osh.prodcal.presentation.fragments.year.MonthListFragment;
import com.osh.prodcal.presentation.fragments.year.YearCalendarFragment;

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

    void inject(MonthListFragment monthListFragment);

    void inject(YearCalendarFragment yearCalendarFragment);
}
