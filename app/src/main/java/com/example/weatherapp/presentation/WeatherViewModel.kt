package com.example.weatherapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.domain.useCase.GetCurrentWeatherUseCase
import com.example.weatherapp.domain.useCase.GetListOfWeather
import com.example.weatherapp.domain.useCase.GetWeatherForecastUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    private val getListOfWeather: GetListOfWeather
) : ViewModel() {

    private val _listOfWeather = MutableLiveData<List<CurrentWeather>>()
    val listOfWeather: LiveData<List<CurrentWeather>> = _listOfWeather

    init {
        viewModelScope.launch {
            _listOfWeather.value = getListOfWeather()
        }
    }

    fun getCurrentWeather(q: String) = viewModelScope.launch {
        getCurrentWeatherUseCase.invoke(q)
    }

    fun getWeatherForecast(q: String, days: Int) = viewModelScope.launch {
        getWeatherForecastUseCase.invoke(q, days)
    }


}