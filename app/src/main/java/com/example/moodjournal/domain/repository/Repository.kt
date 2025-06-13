package com.example.moodjournal.domain.repository

import com.example.moodjournal.data.local.entity.MoodCardEnity
import com.example.moodjournal.data.local.entity.WelcomeEntity

interface Repository {
    suspend fun saveMoodCard(moodCard: MoodCardEnity)

    suspend fun getWelcomeName(): String
    suspend fun saveWelcomeName(welcomeEntity: WelcomeEntity)
}