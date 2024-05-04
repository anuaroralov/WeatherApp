package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.presentation.MyApplication
import com.example.weatherapp.presentation.WeatherListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DomainModule::class, DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(application: MyApplication)

    fun inject(fragment: WeatherListFragment)


    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}