package com.example.moodjournal.domain.usecase

import com.example.moodjournal.domain.repository.Repository
import javax.inject.Inject

class GetNameUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): String = repository.getWelcomeName()

}