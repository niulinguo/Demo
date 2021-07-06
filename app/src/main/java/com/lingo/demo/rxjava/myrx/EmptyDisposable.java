package com.lingo.demo.rxjava.myrx;

public enum EmptyDisposable implements Disposable {
    INSTANCE;

    @Override
    public void dispose() {

    }

    @Override
    public boolean isDisposed() {
        return false;
    }
}
