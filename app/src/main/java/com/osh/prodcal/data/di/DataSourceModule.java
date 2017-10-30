package com.osh.prodcal.data.di;

import android.content.Context;

import com.osh.prodcal.data.repository.source.MonthDataSource;
import com.osh.prodcal.data.repository.source.MonthDataSourceAssets;

import dagger.Module;
import dagger.Provides;


@Module
public class DataSourceModule {

    @Provides
    public MonthDataSource provideMonthDataSource(Context context){
        return new MonthDataSourceAssets(context);
    }

}
