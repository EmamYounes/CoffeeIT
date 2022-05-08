package com.example.commons.application

import android.app.Application
import com.example.commons.network.NetworkConnectionInterceptor
import com.example.commons.network.RetrofitService
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class CoffeeItApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@CoffeeItApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { RetrofitService(instance()) }
    }
}