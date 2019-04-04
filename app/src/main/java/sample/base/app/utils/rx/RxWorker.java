package sample.base.app.utils.rx;

import io.reactivex.Observable;

public class RxWorker {

    public static Observable<Object> doInComputation(final Runnable runnable) {
        return Observable.create(disposable -> {
            runnable.run();
            if (!disposable.isDisposed()) {
                disposable.onNext("We are done bro!");
            }
        }).compose(RxScheduler.applyObservableAsync());
    }

    public static Observable<Object> doInIO(final Runnable runnable) {
        return Observable.create(disposable -> {
            runnable.run();
            if (!disposable.isDisposed()) {
                disposable.onNext("We are done bro!");
            }
        }).compose(RxScheduler.applyObservableAsync());
    }

    public static Observable<Object> doInNewThread(final Runnable runnable) {
        return Observable.create(disposable -> {
            runnable.run();
            if (!disposable.isDisposed()) {
                disposable.onNext("We are done bro!");
            }
        }).compose(RxScheduler.applyObservableAsync());
    }
}

