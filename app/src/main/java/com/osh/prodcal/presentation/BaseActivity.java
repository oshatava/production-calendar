package com.osh.prodcal.presentation;

import com.osh.android.activity.AbstractActivity;
import com.osh.mvp.application.HasAppComponent;
import com.osh.prodcal.application.PCApplication;
import com.osh.prodcal.application.di.AppComponent;

/**
 * Created by oleg on 2/7/2017.
 */

public abstract class BaseActivity extends AbstractActivity<AppComponent> implements HasAppComponent<AppComponent>{

    @Override
    public AppComponent getAppComponent() {
        return PCApplication.getAppComponent(this);
    }

    @Override
    protected void inject() {
        // stub
    }

}
