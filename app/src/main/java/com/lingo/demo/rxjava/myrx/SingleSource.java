package com.lingo.demo.rxjava.myrx;

public interface SingleSource<T> {

    void subscribe(SingleObserver<T> observer);
}
