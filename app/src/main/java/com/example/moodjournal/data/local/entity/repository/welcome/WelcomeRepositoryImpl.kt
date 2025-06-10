package com.example.moodjournal.data.local.entity.repository.welcome

import com.example.moodjournal.data.local.entity.welcome.WelcomeDao
import com.example.moodjournal.data.local.entity.welcome.WelcomeEntity
import com.example.moodjournal.domain.usecase.repository.welcome.WelcomeRepository
import javax.inject.Inject

class WelcomeRepositoryImpl @Inject constructor(
    private val welcomeDao: WelcomeDao
) : WelcomeRepository {
    override suspend fun getWelcomeName(): String {
        return welcomeDao.getWelcomeName(1)?.name ?: "Человечек"
    }
    override suspend fun saveWelcomeName(welcomeEntity: WelcomeEntity) {
        welcomeDao.insertWelcomeName(welcomeEntity)
    }
}