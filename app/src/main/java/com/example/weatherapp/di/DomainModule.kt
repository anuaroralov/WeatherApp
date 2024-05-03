package com.example.weatherapp.di

import com.example.weatherapp.data.RepositoryImpl
import com.example.weatherapp.domain.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DomainModule {

    @Singleton
    @Binds
    fun bindRepository(impl: RepositoryImpl): Repository
}