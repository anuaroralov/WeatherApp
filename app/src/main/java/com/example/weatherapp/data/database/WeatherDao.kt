package com.example.weatherapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather_items")
    fun getShopList(): List<CurrentWeatherDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShopItem(currentWeatherDbModel: CurrentWeatherDbModel)

    @Query("DELETE FROM weather_items WHERE id=:weatherItemId")
    fun deleteShopItem(weatherItemId: Int)

    @Query("SELECT * FROM weather_items WHERE id=:weatherItemId LIMIT 1")
    fun getShopItem(weatherItemId: Int): CurrentWeatherDbModel
}