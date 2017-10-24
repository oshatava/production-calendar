package com.osh.zedsampleapp.presentation.activities;

import com.osh.zedsampleapp.application.ZedApplication;
import com.osh.zedsampleapp.application.di.AppComponent;
import com.osh.zedsampleapp.common.presentation.activity.AbstractActivity;

/**
 * Created by oleg on 2/7/2017.
 */

public abstract class BaseActivity extends AbstractActivity<AppComponent>{

    @Override
    public AppComponent getAppComponent() {
        return ZedApplication.getAppComponent(this);
    }


}
