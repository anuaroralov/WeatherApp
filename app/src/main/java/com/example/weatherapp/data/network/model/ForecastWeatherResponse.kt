package com.example.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ForecastWeatherResponse(
    @SerializedName("location") var location: Location? = null,
    @SerializedName("current") var current: Current? = null,
    @SerializedName("forecast") var forecast: Forecast? = null
)














