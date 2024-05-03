package com.example.weatherapp.di

import androidx.lifecycle.ViewModel
import com.example.weatherapp.presentation.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    @Binds
    fun bindMyViewModel(impl:WeatherViewModel): ViewModel
}