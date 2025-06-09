package com.example.moodjournal.domain.usecase.repository.welcome

interface WelcomeRepository {
    suspend fun getWelcomeName(): String
}