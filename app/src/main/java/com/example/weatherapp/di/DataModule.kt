package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.data.database.WeatherDao
import com.example.weatherapp.data.database.WeatherDatabase
import com.example.weatherapp.data.network.ApiFactory
import com.example.weatherapp.data.network.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return ApiFactory.apiService
    }

    @Singleton
    @Provides
    fun provideShopListDao(application: Application):WeatherDao{
        return WeatherDatabase.getDatabase(application).shopListDao()
    }
}