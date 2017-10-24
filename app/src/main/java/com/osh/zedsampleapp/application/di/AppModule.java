package com.osh.zedsampleapp.application.di;


import android.content.Context;

import com.osh.zedsampleapp.application.Navigator;
import com.osh.zedsampleapp.common.domain.executor.PostExecutionThread;
import com.osh.zedsampleapp.common.domain.executor.ThreadExecutor;
import com.osh.zedsampleapp.data.di.DataModule;
import com.osh.zedsampleapp.domain.di.UseCaseModule;
import com.osh.zedsampleapp.presentation.di.PresentationModule;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by oleg on 2/7/2017.
 */
@Module(includes = {
        DataModule.class,
        UseCaseModule.class,
        PresentationModule.class
})
public class AppModule  {
    private Navigator navigator;

    private Context context;

    public AppModule(Navigator navigator, Context context) {
        this.context = context;
        this.navigator = navigator;
    }

    @Provides
    public Context provideContext(){
        return context;
    }

    @Provides
    public Navigator provideNavigator(){
        return navigator;
    }


    @Provides
    public ThreadExecutor provideThreadExecutor(){
        return new ThreadExecutor() {
            @Override
            public Scheduler getScheduler() {
                return  Schedulers.newThread();
            }
        };
    }

    @Provides
    public PostExecutionThread providePostExecutionThread(){
        return new PostExecutionThread() {
            @Override
            public Scheduler getScheduler() {
                return AndroidSchedulers.mainThread();
            }
        };
    }

}
