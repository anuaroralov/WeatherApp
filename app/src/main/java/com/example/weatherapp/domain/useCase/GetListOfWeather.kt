package com.example.weatherapp.domain.useCase

import com.example.weatherapp.domain.Repository
import javax.inject.Inject

class GetListOfWeather @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke()=repository.getListOfWeather()
}