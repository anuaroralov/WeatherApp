package com.example.weatherapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.domain.useCase.AddToListUseCase
import com.example.weatherapp.domain.useCase.GetCurrentWeatherUseCase
import com.example.weatherapp.domain.useCase.GetListOfWeather
import com.example.weatherapp.domain.useCase.GetWeatherForecastUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    private val getListOfWeather: GetListOfWeather,
    private val addToListUseCase: AddToListUseCase
) : ViewModel() {

    private val _listOfWeather = MutableLiveData<List<CurrentWeather>>()
    val listOfWeather: LiveData<List<CurrentWeather>> = _listOfWeather

    init {
        refreshList()
    }

    fun refreshList(){
        viewModelScope.launch(Dispatchers.IO) {
            val list=getListOfWeather()
            withContext(Dispatchers.Main){
                _listOfWeather.value=list
            }
        }
    }

    fun addToList(currentWeather: CurrentWeather)=viewModelScope.launch(Dispatchers.IO){
        addToListUseCase(currentWeather.location?.name?:"")
    }

    fun getCurrentWeather(q: String) = viewModelScope.launch {
        getCurrentWeatherUseCase.invoke(q)
    }

    fun getWeatherForecast(q: String, days: Int) = viewModelScope.launch {
        getWeatherForecastUseCase.invoke(q, days)
    }


}