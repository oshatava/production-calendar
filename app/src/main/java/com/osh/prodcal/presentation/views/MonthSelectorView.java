package com.osh.prodcal.presentation.views;

import com.osh.prodcal.common.presentation.view.View;
import com.osh.prodcal.domain.MonthKeyEntity;

import java.util.List;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface MonthSelectorView extends View{
    void showMonths(MonthKeyEntity current, List<MonthKeyEntity> months);

    void updateInfo(MonthKeyEntity currentMonth);
}
