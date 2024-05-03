package com.example.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ForecastWeatherResponse(
    @SerializedName("location") var location: LocationDto? = null,
    @SerializedName("current") var current: CurrentDto? = null,
    @SerializedName("forecast") var forecast: ForecastDto? = null
)














