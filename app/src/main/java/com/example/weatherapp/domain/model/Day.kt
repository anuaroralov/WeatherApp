package com.example.weatherapp.domain.model

data class Day(
    val maxTempC: Double? = null,
    val maxTempF: Double? = null,
    val minTempC: Double? = null,
    val minTempF: Double? = null,
    val avgTempC: Double? = null,
    val avgTempF: Double? = null,
    val condition: Condition? = null
)