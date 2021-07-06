package com.lingo.demo.rxjava.myrx;

import java.util.concurrent.ExecutorService;

public final class ExecutorScheduler extends Scheduler {

    private final ExecutorService executorService;

    public ExecutorScheduler(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Disposable schedule(Runnable run) {
        executorService.execute(run);
        return new Disposable() {
            @Override
            public void dispose() {
                executorService.shutdownNow();
            }

            @Override
            public boolean isDisposed() {
                return executorService.isShutdown();
            }
        };
    }
}
