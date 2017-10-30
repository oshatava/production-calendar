package com.osh.prodcal.presentation.views;

import com.osh.prodcal.common.presentation.view.View;
import com.osh.prodcal.domain.MonthEntity;

import java.util.List;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface MonthListView extends View{
    void showMonths(MonthEntity current, List<MonthEntity> months);
}
