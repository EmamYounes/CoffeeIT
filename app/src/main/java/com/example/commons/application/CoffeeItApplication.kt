package com.example.commons.application

import android.app.Application
import com.example.commons.network.NetworkConnectionInterceptor
import com.example.commons.network.ResponseInterceptor
import com.example.commons.network.RetrofitService
import com.example.ui.coffeeit.model.CoffeeBrewRemoteData
import com.example.ui.coffeeit.model.CoffeeBrewRepository
import com.example.ui.coffeeit.viewmodel.CoffeeBrewViewModel
import com.example.ui.coffeeit.viewmodel.CoffeeBrewViewModelFactory
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
        bind() from singleton { ResponseInterceptor() }
        bind() from singleton { RetrofitService(instance(), instance()) }
        bind() from singleton { CoffeeBrewRemoteData(instance()) }
        bind() from singleton { CoffeeBrewRepository(instance()) }
        bind() from singleton { CoffeeBrewViewModelFactory(instance()) }
        bind() from singleton { CoffeeBrewViewModel(instance()) }
    }
}