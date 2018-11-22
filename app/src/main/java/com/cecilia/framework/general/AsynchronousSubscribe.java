package com.cecilia.framework.general;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * @author stone
 */

public abstract class AsynchronousSubscribe<T> implements ObservableOnSubscribe<T> {

    @Override
    public final void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {
        onSubscribe(e);
        e.onComplete();
    }

    /**
     * 订阅的事件，结果通过{@link ObservableEmitter#onNext(T)}传递回UI线程<br>
     * （不需要调用{@link ObservableEmitter#onComplete()}，此方法已实现）
     */
    protected abstract void onSubscribe(@NonNull ObservableEmitter<T> e) throws Exception;

}
