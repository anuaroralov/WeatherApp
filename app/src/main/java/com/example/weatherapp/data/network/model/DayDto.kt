package com.example.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName

data class DayDto(
    @SerializedName("maxtemp_c") var maxtemp_c: Double? = null,
    @SerializedName("maxtemp_f") var maxtemp_f: Double? = null,
    @SerializedName("mintemp_c") var mintemp_c: Double? = null,
    @SerializedName("mintemp_f") var mintemp_f: Double? = null,
    @SerializedName("avgtemp_c") var avgtemp_c: Double? = null,
    @SerializedName("avgtemp_f") var avgtemp_f: Double? = null,
    @SerializedName("condition") var condition: ConditionDto? = null
)