package com.example.weatherapp.presentation

import android.app.Application

class MyApplication : Application() {



    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}