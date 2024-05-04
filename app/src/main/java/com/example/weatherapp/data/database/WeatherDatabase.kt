package com.example.weatherapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CurrentWeatherDbModel::class], version = 1)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun shopListDao(): WeatherDao

    companion object{

        private var INSTANCE:WeatherDatabase?=null
        fun getDatabase(context: Context): WeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    WeatherDatabase::class.java,
                    "app_database")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}