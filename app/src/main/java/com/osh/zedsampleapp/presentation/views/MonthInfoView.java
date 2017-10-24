package com.osh.zedsampleapp.presentation.views;

import com.osh.zedsampleapp.common.presentation.view.View;
import com.osh.zedsampleapp.domain.MonthEntity;

/**
 * Created by olegshatava on 24.10.17.
 */

public interface MonthInfoView extends View {
    void showMonth(MonthEntity monthEntity);
}

