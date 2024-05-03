package com.example.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ForecastDayDto(
    @SerializedName("date") var date: String? = null,
    @SerializedName("day") var day: DayDto? = null,
    @SerializedName("hour") var hour: List<HourDto>? = null
)