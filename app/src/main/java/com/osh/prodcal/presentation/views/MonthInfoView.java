package com.osh.prodcal.presentation.views;

import com.osh.prodcal.common.presentation.view.View;
import com.osh.prodcal.domain.MonthEntity;

/**
 * Created by olegshatava on 24.10.17.
 */

public interface MonthInfoView extends View {
    void showMonth(MonthEntity monthEntity);
}

