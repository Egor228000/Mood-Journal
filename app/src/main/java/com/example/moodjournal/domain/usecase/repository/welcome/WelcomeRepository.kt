package com.example.moodjournal.domain.usecase.repository.welcome

import com.example.moodjournal.data.local.entity.welcome.WelcomeEntity

interface WelcomeRepository {
    suspend fun getWelcomeName(): String
    suspend fun saveWelcomeName(welcomeEntity: WelcomeEntity)
}