package sample.base.app.utils.ext

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import sample.base.app.utils.rx.SchedulerProvider

fun <T> Single<T>.with(schedulerProvider: SchedulerProvider): Single<T>
        = observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())

fun <T> Observable<T>.with(schedulerProvider: SchedulerProvider): Observable<T>
        = observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())

fun Completable.with(schedulerProvider: SchedulerProvider): Completable
        = observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())