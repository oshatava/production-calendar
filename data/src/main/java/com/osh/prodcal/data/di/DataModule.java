package com.osh.prodcal.data.di;

import com.osh.prodcal.data.MonthDataSource;
import com.osh.prodcal.data.MonthRepositoryImpl;
import com.osh.prodcal.domain.repository.MonthRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(includes = {DataSourceModule.class})
public class DataModule {

    @Provides
    @Singleton
    public MonthRepository provideMonthRepository(MonthDataSource monthDataSource){
        return new MonthRepositoryImpl(monthDataSource);
    }

}
