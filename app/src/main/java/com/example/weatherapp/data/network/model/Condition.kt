package com.example.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("text") var text: String? = null,
    @SerializedName("icon") var icon: String? = null
)