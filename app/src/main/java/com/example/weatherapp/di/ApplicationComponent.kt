package com.example.weatherapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DomainModule::class, DataModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}