package com.example.commons.extensions

import com.example.commons.network.Scheduler
import io.reactivex.rxjava3.core.Single

fun <T> Single<T>.performOnBackOutOnMain(scheduler: Scheduler): Single<T> {
    return this.subscribeOn(scheduler.io())
        .observeOn(scheduler.mainThread())
}