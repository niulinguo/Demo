package com.lingo.demo.rxjava.myrx;

import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        Single.just("1")
                .subscribeOn(new ExecutorScheduler(Executors.newSingleThreadExecutor()))
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
