package com.osh.zedsampleapp.presentation.di;

import com.osh.zedsampleapp.application.Navigator;
import com.osh.zedsampleapp.common.domain.executor.PostExecutionThread;
import com.osh.zedsampleapp.common.domain.executor.ThreadExecutor;
import com.osh.zedsampleapp.domain.repository.MonthRepository;
import com.osh.zedsampleapp.domain.usecase.GetMonthEntity;
import com.osh.zedsampleapp.domain.usecase.GetMonthsList;
import com.osh.zedsampleapp.presentation.presenters.MonthCalendarPresenter;
import com.osh.zedsampleapp.presentation.presenters.MonthInfoPresenter;
import com.osh.zedsampleapp.presentation.presenters.MonthSelectorPresenter;
import com.osh.zedsampleapp.presentation.presenters.impl.MonthCalendarPresenterImpl;
import com.osh.zedsampleapp.presentation.presenters.impl.MonthInfoPresenterImpl;
import com.osh.zedsampleapp.presentation.presenters.impl.MonthSelectorPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    @Provides
    public MonthSelectorPresenter provideCalendarPresenter(GetMonthsList getMonthsList){
        return new MonthSelectorPresenterImpl(getMonthsList, null);
    }

    @Provides
    public MonthCalendarPresenter provideMonthCalendarPresenter(GetMonthEntity getMonthEntity){
        return new MonthCalendarPresenterImpl(getMonthEntity, null);
    }

    @Provides
    public MonthInfoPresenter provideMonthInfoPresenter(Navigator navigator, GetMonthEntity getMonthEntity){
        return new MonthInfoPresenterImpl(navigator, getMonthEntity, null);
    }

}
