package com.example.weatherapp.domain.useCase

import com.example.weatherapp.domain.Repository
import javax.inject.Inject

class AddToListUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(name:String)=repository.addToList(name)
}