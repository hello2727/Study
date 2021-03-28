package com.example.android.koin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.koin.R
import com.example.android.koin.viewmodel.MyViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val myViewModel : MyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}