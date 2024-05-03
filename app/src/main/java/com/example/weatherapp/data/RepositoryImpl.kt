package com.example.weatherapp.data

import com.example.weatherapp.data.network.ApiService
import com.example.weatherapp.domain.Repository
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.domain.model.ForecastWeather
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {

    override suspend fun getWeatherForecast(q: String, days: Int): ForecastWeather {
        return apiService.getForecast(q,days).mapToEntity()
    }

    override suspend fun getCurrentWeather(q: String): CurrentWeather {
        return apiService.getWeather(q).mapToEntity()
    }
}