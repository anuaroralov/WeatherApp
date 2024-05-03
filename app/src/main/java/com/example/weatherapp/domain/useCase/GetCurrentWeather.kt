package com.example.weatherapp.domain.useCase

import com.example.weatherapp.domain.Repository
import javax.inject.Inject

class GetCurrentWeather @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(q: String)=repository.getCurrentWeather(q)
}