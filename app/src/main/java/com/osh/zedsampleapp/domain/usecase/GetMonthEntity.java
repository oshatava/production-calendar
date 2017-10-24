package com.osh.zedsampleapp.domain.usecase;

import com.osh.zedsampleapp.common.domain.executor.PostExecutionThread;
import com.osh.zedsampleapp.common.domain.executor.ThreadExecutor;
import com.osh.zedsampleapp.common.domain.usecase.BaseUseCase;
import com.osh.zedsampleapp.data.dto.Holiday;
import com.osh.zedsampleapp.data.dto.Month;
import com.osh.zedsampleapp.domain.MonthEntity;
import com.osh.zedsampleapp.domain.MonthKeyEntity;
import com.osh.zedsampleapp.domain.repository.MonthRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * Created by olegshatava on 22.10.17.
 */

public class GetMonthEntity extends BaseUseCase< MonthEntity, MonthKeyEntity>{

    private MonthRepository monthRepository;

    public GetMonthEntity(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, MonthRepository monthRepository) {
        super(threadExecutor, postExecutionThread);
        this.monthRepository = monthRepository;
    }

    @Override
    public void execute(final MonthKeyEntity data, Consumer<MonthEntity> onData, Consumer<Throwable> onError) {
        buildAndSubscribe(
                Observable.zip(
                        monthRepository.getMonth(data),
                        monthRepository.getHolidays(data.getYear()),
                        (month, holidays) -> new MonthEntity(data.getYear(), holidays, month)),
                onData, onError);
    }



}
