package com.lingo.demo.rxjava.myrx;

import androidx.annotation.NonNull;

public abstract class Single<T> implements SingleSource<T> {

    public static <T> Single<T> just(@NonNull T item) {
        return new SingleJust<>(item);
    }

    @Override
    public final void subscribe(SingleObserver<T> observer) {
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(SingleObserver<T> observer);

    public Single<T> subscribeOn(Scheduler scheduler) {
        return new SingleSubscribeOn<>(this, scheduler);
    }
}
