package com.osh.prodcal.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.osh.prodcal.application.di.AppComponent;
import com.osh.prodcal.application.di.AppModule;
import com.osh.prodcal.application.di.DaggerAppComponent;
import com.osh.prodcal.application.impl.NavigatorImpl;
import com.osh.prodcal.data.di.DataModule;
import com.osh.prodcal.data.di.DataSourceModule;
import com.osh.prodcal.presentation.di.PresentationModule;

/**
 * Created by olegshatava on 23.10.17.
 */

public class PCApplication extends Application{
    public static AppComponent getAppComponent(Context context){
        if(context != null){
            PCApplication app = (PCApplication) context.getApplicationContext();
            return app.appComponent;
        }
        return null;
    }

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        NavigatorImpl navigator = new NavigatorImpl(getApplicationContext());

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(navigator, getApplicationContext()))
                .dataSourceModule(new DataSourceModule())
                .dataModule(new DataModule())
                .presentationModule(new PresentationModule())
                .build();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                navigator.setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                navigator.setCurrentActivity(null);
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}
