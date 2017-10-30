package com.osh.prodcal.common.domain.usecase;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by oleg on 1/24/2017.
 */

public interface UseCase<ResultClass, ParameterClass> extends Disposable {

    void execute(final ParameterClass data, Consumer<ResultClass> onData, Consumer<Throwable> onError);


}
