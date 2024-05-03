package com.example.weatherapp.domain.model

data class ForecastDay(
    val date: String? = null,
    val day: Day? = null,
    val hour: List<Hour>? = null
)