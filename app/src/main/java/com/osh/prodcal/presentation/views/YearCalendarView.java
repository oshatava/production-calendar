package com.osh.prodcal.presentation.views;

import com.osh.prodcal.common.presentation.view.View;
import com.osh.prodcal.domain.MonthEntity;

import java.util.List;

/**
 * Created by olegshatava on 24.10.17.
 */

public interface YearCalendarView extends View {
    void showYear(List<MonthEntity> monthEntities);
}
