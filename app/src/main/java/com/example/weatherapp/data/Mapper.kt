package com.example.weatherapp.data

import com.example.weatherapp.data.network.model.ConditionDto
import com.example.weatherapp.data.network.model.CurrentDto
import com.example.weatherapp.data.network.model.CurrentWeatherResponse
import com.example.weatherapp.data.network.model.DayDto
import com.example.weatherapp.data.network.model.ForecastDayDto
import com.example.weatherapp.data.network.model.ForecastDto
import com.example.weatherapp.data.network.model.ForecastWeatherResponse
import com.example.weatherapp.data.network.model.HourDto
import com.example.weatherapp.data.network.model.LocationDto
import com.example.weatherapp.domain.model.Condition
import com.example.weatherapp.domain.model.Current
import com.example.weatherapp.domain.model.CurrentWeather
import com.example.weatherapp.domain.model.Day
import com.example.weatherapp.domain.model.Forecast
import com.example.weatherapp.domain.model.ForecastDay
import com.example.weatherapp.domain.model.ForecastWeather
import com.example.weatherapp.domain.model.Hour
import com.example.weatherapp.domain.model.Location

internal fun CurrentWeatherResponse.mapToEntity(): CurrentWeather {
    return CurrentWeather(
        location = location?.mapToEntity(),
        current = current?.mapToEntity()
    )
}

internal fun ForecastWeatherResponse.mapToEntity(): ForecastWeather {
    return ForecastWeather(
        location = location?.mapToEntity(),
        current = current?.mapToEntity(),
        forecast = forecast?.mapToEntity()
    )
}

internal fun LocationDto.mapToEntity(): Location {
    return Location(
        name = name,
        region = region,
        country = country,
        localtime = localtime
    )
}

internal fun CurrentDto.mapToEntity(): Current {
    return Current(
        lastUpdated = last_updated,
        tempC = temp_c,
        tempF = temp_f,
        isDay = is_day,
        condition = condition?.mapToEntity(),
        feelsLikeC = feelslike_c,
        feelsLikeF = feelslike_f

    )
}

internal fun ForecastDto.mapToEntity(): Forecast {
    return Forecast(
        forecastDay = forecastday?.map { it.mapToEntity() }
    )
}

internal fun ConditionDto.mapToEntity(): Condition {
    return Condition(
        text = text,
        icon = icon
    )
}

internal fun ForecastDayDto.mapToEntity(): ForecastDay {
    return ForecastDay(
        date = date,
        day = day?.mapToEntity(),
        hour = hour?.map { it.mapToEntity() }
    )

}

internal fun DayDto.mapToEntity(): Day {
    return Day(
        maxTempC = maxtemp_c,
        maxTempF = maxtemp_f,
        minTempC = mintemp_c,
        minTempF = mintemp_f,
        avgTempC = avgtemp_c,
        avgTempF = avgtemp_f,
        condition = condition?.mapToEntity()
    )
}

internal fun HourDto.mapToEntity(): Hour {
    return Hour(
        time = time,
        tempC = temp_c,
        tempF = temp_f,
        isDay = is_day,
        condition = condition?.mapToEntity()
    )

}