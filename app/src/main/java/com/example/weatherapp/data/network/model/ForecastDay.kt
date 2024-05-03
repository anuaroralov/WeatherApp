package com.example.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ForecastDay(
    @SerializedName("date") var date: String? = null,
    @SerializedName("day") var day: Day? = null,
    @SerializedName("hour") var hour: List<Hour>? = null
)