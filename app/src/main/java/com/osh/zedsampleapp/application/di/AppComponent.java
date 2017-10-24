package com.osh.zedsampleapp.application.di;

import com.osh.zedsampleapp.presentation.fragments.MonthCalendarFragment;
import com.osh.zedsampleapp.presentation.fragments.MonthInfoFragment;
import com.osh.zedsampleapp.presentation.fragments.MonthSelectorFragment;

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
}
