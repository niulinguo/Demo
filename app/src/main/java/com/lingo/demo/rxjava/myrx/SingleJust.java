package com.lingo.demo.rxjava.myrx;

public final class SingleJust<T> extends Single<T> {

    private final T value;

    public SingleJust(T value) {
        this.value = value;
    }

    @Override
    protected void subscribeActual(SingleObserver<T> observer) {
        observer.onSubscribe(Disposable.disposed());
        observer.onSuccess(value);
    }
}
