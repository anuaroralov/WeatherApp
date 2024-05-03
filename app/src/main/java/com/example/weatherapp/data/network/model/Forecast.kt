package com.example.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("forecastday") var forecastday: List<ForecastDay>? = null
)