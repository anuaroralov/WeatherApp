package com.example.weatherapp.domain

import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.domain.model.ForecastWeather

interface Repository {

    suspend fun getWeatherForecast(q: String,days: Int): ForecastWeather

    suspend fun getCurrentWeather(q: String): CurrentWeather
}