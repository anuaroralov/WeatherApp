package com.example.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("forecastday") var forecastday: List<ForecastDayDto>? = null
)