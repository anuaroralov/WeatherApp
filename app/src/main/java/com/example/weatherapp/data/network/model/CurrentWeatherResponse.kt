package com.example.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("location") var location: Location? = null,
    @SerializedName("current") var current: Current? = null
)