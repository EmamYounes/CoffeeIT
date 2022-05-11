package com.example.ui.coffeeit.model

import com.example.commons.pojo.CoffeeMachineResponse
import io.reactivex.rxjava3.core.Single

interface CoffeeBrewDataContract {

    interface Repository {
        fun getCoffeeItApi(): Single<CoffeeMachineResponse>
    }

    interface Remote {
        fun getCoffeeItApi(): Single<CoffeeMachineResponse>
    }
}