package com.example.android.databinding_recycler_v.module

import android.app.Application
import com.example.android.databinding_recycler_v.ui.main.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        // Koin Start
        startKoin {
            androidContext(this@App)
            modules(appModule) // 사용할 Module 등록
        }

        // 복수개(여러개) 모듈 등록 시
        // modules(a_Module, b_Module, c_Module)
    }
}