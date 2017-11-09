package com.osh.prodcal.presentation.views;

import com.osh.mvp.view.View;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.presentation.presenters.YearCalendarPresenter;

import java.util.List;

/**
 * Created by olegshatava on 24.10.17.
 */

public interface YearCalendarView extends View<YearCalendarPresenter> {
    void showYear(List<MonthEntity> monthEntities);
}
