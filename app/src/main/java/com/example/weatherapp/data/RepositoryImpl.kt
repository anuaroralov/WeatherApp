package com.example.weatherapp.data

import com.example.weatherapp.data.database.WeatherDao
import com.example.weatherapp.data.network.ApiService
import com.example.weatherapp.data.network.model.CurrentWeatherResponse
import com.example.weatherapp.domain.Repository
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.domain.model.ForecastWeather
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val weatherDao: WeatherDao,
    private val apiService: ApiService
) : Repository {

    override suspend fun getWeatherForecast(q: String, days: Int): ForecastWeather {
        return apiService.getForecast(q,days).mapToEntity()
    }

    override suspend fun getCurrentWeather(q: String): CurrentWeather {
        return apiService.getWeather(q).mapToEntity()
    }

    override suspend fun getListOfWeather():List<CurrentWeather> {
        val cities = weatherDao.getShopList()

        val weatherResults = mutableListOf<CurrentWeatherResponse>()

        cities.forEach { city ->
            try {
                val weatherResponse = apiService.getWeather(city.name)
                weatherResults.add(weatherResponse)
            } catch (e: Exception) {
                println("Error fetching weather for city ${city.name}: ${e.message}")
            }
        }

        return weatherResults.map{it.mapToEntity()}
    }



}