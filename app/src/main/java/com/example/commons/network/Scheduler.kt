package com.example.commons.network

import io.reactivex.rxjava3.core.Scheduler


/**
 *  Interface to mock different threads during testing.
 * */
interface Scheduler {
    fun mainThread(): Scheduler
    fun io(): Scheduler
}