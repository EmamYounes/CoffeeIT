package com.example.commons.extensions

import android.os.Handler
import android.os.Looper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

fun <T> Single<T>.performOnBackOutOnMain(): Single<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}

inline fun delay(delay: Long, crossinline completion: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        completion()
    }, delay)
}