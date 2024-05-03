package com.example.weatherapp.data.network



import com.example.weatherapp.data.network.model.CurrentWeatherResponse
import com.example.weatherapp.data.network.model.ForecastWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("current.json")
    suspend fun getWeather(
        @Query("q") q: String,
    ): CurrentWeatherResponse

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("q") q: String,
        @Query("days") days: Int
    ): ForecastWeatherResponse
}