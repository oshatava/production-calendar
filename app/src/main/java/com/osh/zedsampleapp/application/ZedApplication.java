package com.osh.zedsampleapp.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.osh.zedsampleapp.application.di.AppComponent;
import com.osh.zedsampleapp.application.di.AppModule;
import com.osh.zedsampleapp.application.di.DaggerAppComponent;
import com.osh.zedsampleapp.application.impl.NavigatorImpl;
import com.osh.zedsampleapp.data.di.DataModule;
import com.osh.zedsampleapp.data.di.DataSourceModule;
import com.osh.zedsampleapp.domain.di.UseCaseModule;
import com.osh.zedsampleapp.presentation.di.PresentationModule;

/**
 * Created by olegshatava on 23.10.17.
 */

public class ZedApplication extends Application{
    public static AppComponent getAppComponent(Context context){
        if(context != null){
            ZedApplication app = (ZedApplication) context.getApplicationContext();
            return app.appComponent;
        }
        return null;
    }

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(new NavigatorImpl(getApplicationContext()), getApplicationContext()))
                .dataSourceModule(new DataSourceModule())
                .dataModule(new DataModule())
                .presentationModule(new PresentationModule())
                .useCaseModule(new UseCaseModule())
                .build();


    }
}
