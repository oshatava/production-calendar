package com.osh.prodcal.domain.usecase;

import com.osh.prodcal.common.domain.executor.PostExecutionThread;
import com.osh.prodcal.common.domain.executor.ThreadExecutor;
import com.osh.prodcal.common.domain.usecase.BaseUseCase;
import com.osh.prodcal.domain.MonthKeyEntity;
import com.osh.prodcal.domain.repository.MonthRepository;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by olegshatava on 22.10.17.
 */

public class SetCurrentMonth extends BaseUseCase<MonthKeyEntity, MonthKeyEntity, MonthRepository> {

    @Inject
    public SetCurrentMonth(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, MonthRepository monthRepository) {
        super(threadExecutor, postExecutionThread, monthRepository);
    }

    @Override
    public void execute(final MonthKeyEntity data, Consumer<MonthKeyEntity> onData, Consumer<Throwable> onError) {
        buildAndSubscribe(getRepository().setCurrentMonth(data), onData, onError);
    }
}
