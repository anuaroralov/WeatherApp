package com.example.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.useCase.GetCurrentWeatherUseCase
import com.example.weatherapp.domain.useCase.GetWeatherForecastUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase
) : ViewModel() {



    fun getCurrentWeather(q: String) = viewModelScope.launch {
        getCurrentWeatherUseCase.invoke(q)
    }

    fun getWeatherForecast(q: String, days: Int) = viewModelScope.launch {
        getWeatherForecastUseCase.invoke(q, days)
    }


}