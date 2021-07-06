package com.lingo.demo.rxjava.myrx;

public final class SingleSubscribeOn<T> extends Single<T> {
    private final SingleSource<T> source;
    private final Scheduler scheduler;

    public SingleSubscribeOn(SingleSource<T> source, Scheduler scheduler) {
        this.source = source;
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(SingleObserver<T> observer) {
        final SubscribeOnObserver<T> parent = new SubscribeOnObserver<>(observer, source);
        observer.onSubscribe(parent);
        parent.disposable = scheduler.schedule(parent);
    }

    static final class SubscribeOnObserver<T> implements SingleObserver<T>, Runnable, Disposable {

        private final SingleObserver<T> downstream;
        private final SingleSource<T> source;
        private Disposable upDisposable;
        private Disposable disposable;

        SubscribeOnObserver(SingleObserver<T> downstream, SingleSource<T> source) {
            this.downstream = downstream;
            this.source = source;
            this.disposable = EmptyDisposable.INSTANCE;
        }

        @Override
        public void onSubscribe(Disposable d) {
            upDisposable = d;
        }

        @Override
        public void onSuccess(T t) {
            downstream.onSuccess(t);
        }

        @Override
        public void onError(Throwable e) {
            downstream.onError(e);
        }

        @Override
        public void run() {
            source.subscribe(this);
        }

        @Override
        public void dispose() {
            upDisposable.dispose();
            disposable.dispose();
        }

        @Override
        public boolean isDisposed() {
            return upDisposable.isDisposed();
        }
    }
}
