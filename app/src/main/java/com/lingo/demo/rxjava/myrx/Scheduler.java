package com.lingo.demo.rxjava.myrx;

public abstract class Scheduler {

    public abstract Disposable schedule(Runnable run);
}
