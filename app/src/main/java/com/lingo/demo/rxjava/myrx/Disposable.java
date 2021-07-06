package com.lingo.demo.rxjava.myrx;

public interface Disposable {

    void dispose();

    boolean isDisposed();

    static Disposable disposed() {
        return EmptyDisposable.INSTANCE;
    }

}
