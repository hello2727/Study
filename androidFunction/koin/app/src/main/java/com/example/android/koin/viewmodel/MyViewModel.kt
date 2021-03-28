package com.example.android.koin.viewmodel

import androidx.lifecycle.ViewModel
import com.example.android.koin.repository.model.HelloRepository

class MyViewModel(val repo : HelloRepository) : ViewModel() {
    fun sayHello() : String {
        return "${repo.giveHello()} from $this"
    }
}