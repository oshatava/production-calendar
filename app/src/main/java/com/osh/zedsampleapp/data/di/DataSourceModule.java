package com.osh.zedsampleapp.data.di;

import android.content.Context;

import com.osh.zedsampleapp.data.repository.MonthRepositoryImpl;
import com.osh.zedsampleapp.data.repository.source.MonthDataSource;
import com.osh.zedsampleapp.data.repository.source.MonthDataSourceAssets;
import com.osh.zedsampleapp.domain.repository.MonthRepository;

import dagger.Module;
import dagger.Provides;


@Module
public class DataSourceModule {

    @Provides
    public MonthDataSource provideMonthDataSource(Context context){
        return new MonthDataSourceAssets(context);
    }

}
