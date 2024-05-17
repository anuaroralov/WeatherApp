package com.example.weatherapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.domain.model.ForecastWeather
import com.example.weatherapp.domain.useCase.AddToListUseCase
import com.example.weatherapp.domain.useCase.DeleteFromListUseCase
import com.example.weatherapp.domain.useCase.GetCurrentWeatherUseCase
import com.example.weatherapp.domain.useCase.GetListOfWeather
import com.example.weatherapp.domain.useCase.GetWeatherForecastUseCase
import com.example.weatherapp.domain.useCase.IsWeatherItemExistsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    private val getListOfWeather: GetListOfWeather,
    private val addToListUseCase: AddToListUseCase,
    private val deleteFromListUseCase: DeleteFromListUseCase,
    private val isWeatherItemExistsUseCase: IsWeatherItemExistsUseCase,
    private val networkChecker: NetworkChecker
) : ViewModel() {

    private val _listOfWeather = MutableLiveData<List<CurrentWeather>>()
    val listOfWeather: LiveData<List<CurrentWeather>> = _listOfWeather

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessages = MutableLiveData<String>()
    val errorMessages: LiveData<String> = _errorMessages

    private val _suggestions = MutableLiveData<List<String>>()
    val suggestions: LiveData<List<String>> = _suggestions

    private val _internetConnection = MutableLiveData<Boolean>(true)
    val internetConnection: LiveData<Boolean> = _internetConnection

    init {
        refreshList()
    }

    fun refreshList() {
        if (isInternetAvailable()) {
            _isLoading.postValue(true)
            _internetConnection.postValue(true)
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val listOfWeather = getListOfWeather()
                    _listOfWeather.postValue(listOfWeather)
                } catch (e: Exception) {
                    postErrorMessage("Error refreshing list: ${e.message}")
                } finally {
                    _isLoading.postValue(false)
                }
            }
        } else {
            _internetConnection.postValue(false)
            postErrorMessage("Internet is not available")
        }
    }

    private fun isInternetAvailable(): Boolean {
        return networkChecker.isInternetAvailable()
    }

    suspend fun getWeatherForecast(location: String): ForecastWeather {
        _isLoading.postValue(true)
        return try {
            val forecast = getWeatherForecastUseCase(location, 7)
            forecast
        } finally {
            _isLoading.postValue(false)
        }
    }

    private fun postErrorMessage(message: String) {
        _errorMessages.postValue(message)
    }

    fun getCurrentWeather(query: String) {
        viewModelScope.launch {
            try {
                val weatherResponse = getCurrentWeatherUseCase.invoke(query)
                val suggestions = weatherResponse?.let { listOf(it) } ?: emptyList()
                _suggestions.postValue(suggestions)
            } catch (e: Exception) {
                if (e.message.toString().trim() != "HTTP 400".toString().trim()) {
                    postErrorMessage("Error fetching weather: ${e.message}")
                }
            }
        }
    }

    fun addToList(name: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            addToListUseCase(name)
            refreshList()
        } catch (e: Exception) {
            postErrorMessage("Error adding to list: ${e.message}")
        }
    }

    fun deleteFromList(name: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            deleteFromListUseCase(name)
            refreshList()
        } catch (e: Exception) {
            postErrorMessage("Error deleting from list: ${e.message}")
        }
    }

    suspend fun isWeatherItemExists(name: String): Boolean {
        return withContext(Dispatchers.IO) {
            isWeatherItemExistsUseCase(name)
        }
    }

    fun clearErrorMessage() {
        _errorMessages.value = ""
    }

    fun clearSuggestions() {
        _suggestions.value = emptyList()
    }
}

