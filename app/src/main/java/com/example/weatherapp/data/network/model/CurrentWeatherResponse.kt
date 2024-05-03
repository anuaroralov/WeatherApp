package com.example.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("location") var location: LocationDto? = null,
    @SerializedName("current") var current: CurrentDto? = null
)