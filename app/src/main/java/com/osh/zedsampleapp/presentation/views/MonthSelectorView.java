package com.osh.zedsampleapp.presentation.views;

import com.osh.zedsampleapp.common.presentation.view.View;
import com.osh.zedsampleapp.data.dto.Day;
import com.osh.zedsampleapp.domain.MonthKeyEntity;

import java.util.List;

/**
 * Created by olegshatava on 23.10.17.
 */

public interface MonthSelectorView extends View{
    void showMonths(MonthKeyEntity current, List<MonthKeyEntity> months);

    void updateInfo(MonthKeyEntity currentMonth);
}
