package com.example.moodjournal.data.repository.impl

import com.example.moodjournal.data.local.dao.MoodCardDao
import com.example.moodjournal.data.local.dao.UserNameDao
import com.example.moodjournal.data.local.entity.MoodCardEnity
import com.example.moodjournal.data.local.entity.WelcomeEntity
import com.example.moodjournal.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val moodCardDao: MoodCardDao,
    private val userNameDao: UserNameDao
) : Repository {
    override suspend fun getWelcomeName(): String {
        return userNameDao.getWelcomeName(1)?.name ?: "Человечек"
    }
    override suspend fun saveWelcomeName(welcomeEntity: WelcomeEntity) {
        userNameDao.insertWelcomeName(welcomeEntity)
    }
    override suspend fun saveMoodCard(moodCard: MoodCardEnity) {
        moodCardDao.insertMoodCard(moodCard)
    }
}