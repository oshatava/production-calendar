package com.osh.zedsampleapp.presentation.views;

import com.osh.zedsampleapp.common.presentation.view.View;
import com.osh.zedsampleapp.domain.MonthEntity;
import com.osh.zedsampleapp.domain.MonthKeyEntity;

import java.util.List;

/**
 * Created by olegshatava on 24.10.17.
 */

public interface MonthCalendarView extends View {
    void showMonth(MonthEntity monthEntity);
}
