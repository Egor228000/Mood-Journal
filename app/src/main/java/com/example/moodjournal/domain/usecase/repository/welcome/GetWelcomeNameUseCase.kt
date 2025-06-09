package com.example.moodjournal.domain.usecase.repository.welcome

import javax.inject.Inject

class GetWelcomeNameUseCase @Inject constructor(
    private val repository: WelcomeRepository
) {
    suspend operator fun invoke(): String = repository.getWelcomeName()
}