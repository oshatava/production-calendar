package com.osh.zedsampleapp.domain.di;


import com.osh.zedsampleapp.common.domain.executor.PostExecutionThread;
import com.osh.zedsampleapp.common.domain.executor.ThreadExecutor;
import com.osh.zedsampleapp.domain.repository.MonthRepository;
import com.osh.zedsampleapp.domain.usecase.GetMonthEntity;
import com.osh.zedsampleapp.domain.usecase.GetMonthsList;

import dagger.Module;
import dagger.Provides;


@Module
public class UseCaseModule {

    @Provides
    public GetMonthEntity provideGetMonthEntity(ThreadExecutor threadExecutor,
                                                PostExecutionThread postExecutionThread,
                                                MonthRepository monthRepository){
        return new GetMonthEntity(threadExecutor, postExecutionThread, monthRepository);
    }

    @Provides
    public GetMonthsList provideGetMonthsList(ThreadExecutor threadExecutor,
                                               PostExecutionThread postExecutionThread,
                                               MonthRepository monthRepository){
        return new GetMonthsList(threadExecutor, postExecutionThread, monthRepository);
    }


}
