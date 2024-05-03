package com.example.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CurrentDto(
    @SerializedName("last_updated") var last_updated: String? = null,
    @SerializedName("temp_c") var temp_c: Double? = null,
    @SerializedName("temp_f") var temp_f: Double? = null,
    @SerializedName("is_day") var is_day: Int? = null,
    @SerializedName("condition") var condition: ConditionDto? = null,
    @SerializedName("feelslike_c") var feelslike_c: Double? = null,
    @SerializedName("feelslike_f") var feelslike_f: Double? = null
)