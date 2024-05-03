package com.example.weatherapp.data.network.model

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("name") var name: String? = null,
    @SerializedName("region") var region: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("localtime") var localtime: String? = null
)