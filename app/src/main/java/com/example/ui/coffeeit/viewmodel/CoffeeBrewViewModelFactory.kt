package com.example.ui.coffeeit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ui.coffeeit.model.CoffeeBrewRepository

class CoffeeBrewViewModelFactory(
    private val repository: CoffeeBrewRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CoffeeBrewViewModel(repository) as T
    }
}