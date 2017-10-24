package com.osh.zedsampleapp.common.domain.usecase;


import com.osh.zedsampleapp.common.domain.executor.PostExecutionThread;
import com.osh.zedsampleapp.common.domain.executor.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by oleg on 1/24/2017.
 */

public abstract class BaseUseCase<ResultClass, ParameterClass> implements UseCase<ResultClass, ParameterClass> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable disposables;

    public BaseUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.disposables = new CompositeDisposable();
    }

    public abstract void execute(final ParameterClass data, Consumer<ResultClass> onData, Consumer<Throwable> onError);

    protected void buildAndSubscribe(Observable<ResultClass> action, Consumer<ResultClass> onNext, Consumer<Throwable> onError) {
        final Observable<ResultClass> observable = action
                .subscribeOn(threadExecutor.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
        addDisposable(observable.subscribe (onNext, onError));
    }

    @Override
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    @Override
    public boolean isDisposed() {
        return disposables.isDisposed();
    }

    private void addDisposable(Disposable disposable) {
        if(disposable!=null && disposables!=null) {
            disposables.add(disposable);
        }
    }
}
