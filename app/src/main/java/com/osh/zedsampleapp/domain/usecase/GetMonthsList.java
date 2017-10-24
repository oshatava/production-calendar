package com.osh.zedsampleapp.domain.usecase;

import com.osh.zedsampleapp.common.domain.executor.PostExecutionThread;
import com.osh.zedsampleapp.common.domain.executor.ThreadExecutor;
import com.osh.zedsampleapp.common.domain.usecase.BaseUseCase;
import com.osh.zedsampleapp.data.dto.Holiday;
import com.osh.zedsampleapp.data.dto.Month;
import com.osh.zedsampleapp.data.dto.MonthKey;
import com.osh.zedsampleapp.domain.MonthEntity;
import com.osh.zedsampleapp.domain.MonthKeyEntity;
import com.osh.zedsampleapp.domain.repository.MonthRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * Created by olegshatava on 22.10.17.
 */

public class GetMonthsList extends BaseUseCase<List<MonthKeyEntity>, Void>{

    private MonthRepository monthRepository;

    public GetMonthsList(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, MonthRepository monthRepository) {
        super(threadExecutor, postExecutionThread);
        this.monthRepository = monthRepository;
    }

    @Override
    public void execute(final Void data, Consumer<List<MonthKeyEntity>> onData, Consumer<Throwable> onError) {
        buildAndSubscribe(monthRepository.getMonthsIds(), onData, onError);
    }

}
