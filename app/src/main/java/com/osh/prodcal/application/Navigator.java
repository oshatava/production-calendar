package com.osh.prodcal.application;

import com.osh.prodcal.data.dto.Holiday;

/**
 * Created by olegshatava on 24.10.17.
 */

public interface Navigator {
    public static final int REQUEST_SHOW_MONTH_SELECTOR = 1;

    void showHolidayInfo(Holiday holiday);
    void showMonthSelectorActivity();
}
