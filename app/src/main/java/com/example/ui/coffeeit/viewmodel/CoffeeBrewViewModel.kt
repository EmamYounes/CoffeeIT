package com.example.ui.coffeeit.viewmodel

import androidx.lifecycle.ViewModel
import com.example.commons.pojo.CoffeeItResponse
import com.example.ui.coffeeit.data_classes.OverviewDataItem
import com.example.ui.coffeeit.model.CoffeeBrewRepository
import com.jakewharton.rxrelay3.BehaviorRelay

class CoffeeBrewViewModel(
    private val repository: CoffeeBrewRepository
) : ViewModel() {

    var errorGetCoffeeItApi = BehaviorRelay.create<String>()
    var successGetCoffeeItApi = BehaviorRelay.create<CoffeeItResponse>()
    var overviewList = BehaviorRelay.create<List<OverviewDataItem>>()

    fun getCoffeeItApi() {
        repository.getCoffeeItApi()
            .doOnError { errorGetCoffeeItApi.accept(it.localizedMessage!!.toString()) }
            .subscribe({
                it.let { response ->
                    successGetCoffeeItApi.accept(response)
                }
            }, { throwable ->
                errorGetCoffeeItApi.accept(throwable.localizedMessage!!.toString())
            })

    }
}