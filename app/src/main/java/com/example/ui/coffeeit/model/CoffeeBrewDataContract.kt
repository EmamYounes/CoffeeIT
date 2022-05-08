package com.example.ui.coffeeit.model

import com.example.commons.pojo.CoffeeItResponse
import io.reactivex.rxjava3.core.Single

interface CoffeeBrewDataContract {

    interface Repository {
        fun getCoffeeItApi(): Single<CoffeeItResponse>
    }

    interface Remote {
        fun getCoffeeItApi(): Single<CoffeeItResponse>
    }
}