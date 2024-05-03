package com.example.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ConditionDto(
    @SerializedName("text") var text: String? = null,
    @SerializedName("icon") var icon: String? = null
)