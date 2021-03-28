package com.example.android.koin.util.module

import com.example.android.koin.repository.model.HelloRepository
import com.example.android.koin.repository.model.HelloRepositoryImpl
import com.example.android.koin.viewmodel.MyViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

class module {
    val appModule = module {
        // single instance of HelloRepository
        single<HelloRepository> { HelloRepositoryImpl() }

        // MyViewModel ViewModel
        viewModel {
            MyViewModel(get())
        }
    }
}