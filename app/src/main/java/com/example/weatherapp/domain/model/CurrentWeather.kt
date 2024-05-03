package com.example.weatherapp.domain.model

data class CurrentWeather(
    val location: Location? = null,
    val current: Current? = null
)