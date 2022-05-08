package com.example.ui.coffeeit.model

import com.example.commons.extensions.performOnBackOutOnMain
import com.example.commons.network.Scheduler
import com.example.commons.pojo.CoffeeItResponse
import io.reactivex.rxjava3.core.Single

class CoffeeBrewRepository(
    private val remote: CoffeeBrewDataContract.Remote,
    private val scheduler: Scheduler
) : CoffeeBrewDataContract.Repository {
    override fun getCoffeeItApi(): Single<CoffeeItResponse> {
        return remote.getCoffeeItApi().performOnBackOutOnMain(scheduler)
    }
}