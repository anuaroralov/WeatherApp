package com.example.weatherapp.domain.model

data class Current(
    val lastUpdated: String? = null,
    val tempC: Double? = null,
    val tempF: Double? = null,
    val isDay: Int? = null,
    val condition: Condition? = null,
    val feelsLikeC: Double? = null,
    val feelsLikeF: Double? = null
)