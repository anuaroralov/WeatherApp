package com.example.weatherapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather_items")
    fun getWeatherList(): List<CurrentWeatherDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherItem(currentWeatherDbModel: CurrentWeatherDbModel)

    @Query("DELETE FROM weather_items WHERE name=:weatherItemId")
    fun deleteWeatherItem(weatherItemId: String)

    @Query("SELECT * FROM weather_items WHERE name=:weatherItemId LIMIT 1")
    fun getWeatherItem(weatherItemId: String): CurrentWeatherDbModel
}