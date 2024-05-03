package com.example.weatherapp.di

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
}