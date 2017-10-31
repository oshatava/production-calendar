package com.osh.prodcal.presentation.di;

import com.osh.prodcal.application.Navigator;
import com.osh.prodcal.domain.usecase.GetMonthEntitiesList;
import com.osh.prodcal.domain.usecase.GetMonthEntitiesListForYear;
import com.osh.prodcal.domain.usecase.GetMonthEntity;
import com.osh.prodcal.domain.usecase.GetMonthsList;
import com.osh.prodcal.domain.usecase.ObserveCurrentMonth;
import com.osh.prodcal.domain.usecase.SetCurrentMonth;
import com.osh.prodcal.presentation.presenters.MonthCalendarPresenter;
import com.osh.prodcal.presentation.presenters.MonthInfoPresenter;
import com.osh.prodcal.presentation.presenters.MonthListPresenter;
import com.osh.prodcal.presentation.presenters.MonthSelectorPresenter;
import com.osh.prodcal.presentation.presenters.YearCalendarPresenter;
import com.osh.prodcal.presentation.presenters.impl.MonthCalendarPresenterImpl;
import com.osh.prodcal.presentation.presenters.impl.MonthInfoPresenterImpl;
import com.osh.prodcal.presentation.presenters.impl.MonthListPresenterImpl;
import com.osh.prodcal.presentation.presenters.impl.MonthSelectorPresenterImpl;
import com.osh.prodcal.presentation.presenters.impl.YearCalendarPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    @Provides
    public MonthSelectorPresenter provideCalendarPresenter(Navigator navigator,
                                                           GetMonthsList getMonthsList,
                                                           ObserveCurrentMonth observeCurrentMonth,
                                                           SetCurrentMonth setCurrentMonth){
        return new MonthSelectorPresenterImpl(navigator, getMonthsList, observeCurrentMonth, setCurrentMonth, null);
    }

    @Provides
    public MonthCalendarPresenter provideMonthCalendarPresenter(GetMonthEntity getMonthEntity){
        return new MonthCalendarPresenterImpl(getMonthEntity, null);
    }

    @Provides
    public MonthInfoPresenter provideMonthInfoPresenter(Navigator navigator,
                                                        GetMonthEntity getMonthEntity,
                                                        ObserveCurrentMonth observeCurrentMonth){
        return new MonthInfoPresenterImpl(navigator, getMonthEntity, observeCurrentMonth, null);
    }

    @Provides
    public MonthListPresenter provideMonthListPresenter(Navigator navigator,
                                                        GetMonthEntitiesList getMonthEntitiesList,
                                                        ObserveCurrentMonth observeCurrentMonth,
                                                        SetCurrentMonth setCurrentMonth){
        return new MonthListPresenterImpl(navigator, getMonthEntitiesList, observeCurrentMonth, setCurrentMonth);
    }

    @Provides
    public YearCalendarPresenter provideYearCalendarPresenter(Navigator navigator,
                                                              GetMonthEntitiesListForYear getMonthEntitiesListForYear,
                                                              SetCurrentMonth setCurrentMonth){
        return new YearCalendarPresenterImpl(navigator, null, getMonthEntitiesListForYear, setCurrentMonth);
    }

}
