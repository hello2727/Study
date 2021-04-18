package com.example.android.databinding_recycler_v.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val isPowerOn = MutableLiveData<Boolean>()

    fun onPowerButtonClick() {
        val curStatus = isPowerOn.value
        if (curStatus == true) {
            isPowerOn.value = false
        } else {
            isPowerOn.value = true
        }
    }
}