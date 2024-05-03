package com.example.weatherapp.domain.model

data class Hour(
    val time: String? = null,
    val tempC: Double? = null,
    val tempF: Double? = null,
    val isDay: Int? = null,
    val condition: Condition? = null
)