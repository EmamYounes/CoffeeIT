package com.example.ui.coffeeit.model

import com.example.commons.extensions.performOnBackOutOnMain
import com.example.commons.pojo.CoffeeItResponse
import io.reactivex.rxjava3.core.Single

class CoffeeBrewRepository(
    private val remote: CoffeeBrewDataContract.Remote
) : CoffeeBrewDataContract.Repository {
    override fun getCoffeeItApi(): Single<CoffeeItResponse> {
        return remote.getCoffeeItApi().performOnBackOutOnMain()
    }
}