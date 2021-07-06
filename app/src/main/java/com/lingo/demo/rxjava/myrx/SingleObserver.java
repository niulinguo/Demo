package com.lingo.demo.rxjava.myrx;

public interface SingleObserver<T> {

    void onSubscribe(Disposable d);

    void onSuccess(T t);

    void onError(Throwable e);

}
