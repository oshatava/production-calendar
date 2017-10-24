package com.osh.zedsampleapp.application.impl;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;

import com.osh.zedsampleapp.application.Navigator;
import com.osh.zedsampleapp.data.dto.Holiday;

/**
 * Created by olegshatava on 24.10.17.
 */

public class NavigatorImpl implements Navigator {
    private Context context;

    public NavigatorImpl(Context context) {
        this.context = context;
    }

    @Override
    public void showHolidayInfo(Holiday holiday) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, holiday.getTitle());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
