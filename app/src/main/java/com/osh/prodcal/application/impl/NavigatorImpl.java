package com.osh.prodcal.application.impl;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;

import com.osh.prodcal.application.Navigator;
import com.osh.prodcal.data.dto.Holiday;
import com.osh.prodcal.presentation.activities.YearViewActivity;

import java.lang.ref.WeakReference;

/**
 * Created by olegshatava on 24.10.17.
 */

public class NavigatorImpl implements Navigator {
    private Context context;
    private WeakReference<Activity> currentActivity = new WeakReference<Activity>(null);

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = new WeakReference<Activity>(currentActivity);
    }

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

    @Override
    public void showMonthSelectorActivity() {
        Activity activity = currentActivity.get();
        if(activity!=null) {
            Intent intent = new Intent(activity, YearViewActivity.class);
            activity.startActivityForResult(intent, REQUEST_SHOW_MONTH_SELECTOR);
        }
    }
}
