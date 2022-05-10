package com.example.ui.coffeeit.model

import com.example.commons.network.RetrofitService
import com.example.commons.pojo.CoffeeItResponse
import com.example.commons.utilities.Constant
import io.reactivex.rxjava3.core.Single

class CoffeeBrewRemoteData(private val service: RetrofitService) :
    CoffeeBrewDataContract.Remote {
    override fun getCoffeeItApi(): Single<CoffeeItResponse> {
        return service.getCoffeeItApi("coffee-machine/" + Constant.COFFEE_MACHINE_ID)
    }
}