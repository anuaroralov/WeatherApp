package com.example.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName

data class Hour(
    @SerializedName("time") var time: String? = null,
    @SerializedName("temp_c") var temp_c: Double? = null,
    @SerializedName("temp_f") var temp_f: Double? = null,
    @SerializedName("is_day") var is_day: Int? = null,
    @SerializedName("condition") var condition: Condition? = null
)