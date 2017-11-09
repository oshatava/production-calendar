package com.osh.prodcal.domain.usecase;

import com.osh.mvp.domain.executor.PostExecutionThread;
import com.osh.mvp.domain.executor.ThreadExecutor;
import com.osh.mvp.domain.usecase.BaseUseCase;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.domain.repository.MonthRepository;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by olegshatava on 22.10.17.
 */

public class ObserveCurrentMonth extends BaseUseCase<MonthKeyEntity, Void, MonthRepository> {

    @Inject
    public ObserveCurrentMonth(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, MonthRepository monthRepository) {
        super(threadExecutor, postExecutionThread, monthRepository);
    }

    @Override
    public void execute(final Void data, Consumer<MonthKeyEntity> onData, Consumer<Throwable> onError) {
        buildAndSubscribe(getRepository().observeCurrentMonth(), onData, onError);
    }
}
