package com.osh.prodcal.presentation.activities;

import com.osh.prodcal.application.PCApplication;
import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.common.presentation.activity.AbstractActivity;

/**
 * Created by oleg on 2/7/2017.
 */

public abstract class BaseActivity extends AbstractActivity<AppComponent>{

    @Override
    public AppComponent getAppComponent() {
        return PCApplication.getAppComponent(this);
    }


}
