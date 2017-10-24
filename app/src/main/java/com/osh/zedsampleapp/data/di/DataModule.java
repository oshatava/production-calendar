package com.osh.zedsampleapp.data.di;

import com.osh.zedsampleapp.data.repository.MonthRepositoryImpl;
import com.osh.zedsampleapp.data.repository.source.MonthDataSource;
import com.osh.zedsampleapp.domain.repository.MonthRepository;

import dagger.Module;
import dagger.Provides;


@Module(includes = {DataSourceModule.class})
public class DataModule {

    @Provides
    public MonthRepository provideMonthRepository(MonthDataSource monthDataSource){
        return new MonthRepositoryImpl(monthDataSource);
    }

}
