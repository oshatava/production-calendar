package com.osh.prodcal.application;


import com.osh.prodcal.domain.dto.Holiday;

/**
 * Created by olegshatava on 24.10.17.
 */

public interface Navigator {

    void showHolidayInfo(Holiday holiday);
    void showMonthSelectorActivity();

    void close();
}
