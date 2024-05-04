package com.example.weatherapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_items")
data class CurrentWeatherDbModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
)