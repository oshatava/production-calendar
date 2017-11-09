package com.osh.prodcal.domain.usecase;

import com.osh.mvp.domain.executor.PostExecutionThread;
import com.osh.mvp.domain.executor.ThreadExecutor;
import com.osh.mvp.domain.usecase.BaseUseCase;
import com.osh.prodcal.domain.MonthEntity;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.domain.repository.MonthRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by olegshatava on 22.10.17.
 */

public class GetMonthEntity extends BaseUseCase< MonthEntity, MonthKeyEntity, MonthRepository>{


    @Inject
    public GetMonthEntity(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, MonthRepository monthRepository) {
        super(threadExecutor, postExecutionThread, monthRepository);
    }

    @Override
    public void execute(final MonthKeyEntity data, Consumer<MonthEntity> onData, Consumer<Throwable> onError) {
        buildAndSubscribe(
                Observable.zip(
                        getRepository().getMonth(data),
                        getRepository().getHolidays(data.getYear()),
                        (month, holidays) -> new MonthEntity(data.getYear(), holidays, month)),
                onData, onError);
    }



}
