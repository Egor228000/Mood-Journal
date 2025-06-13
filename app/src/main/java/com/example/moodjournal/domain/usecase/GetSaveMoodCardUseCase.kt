package com.example.moodjournal.domain.usecase

import com.example.moodjournal.data.local.entity.MoodCardEnity
import com.example.moodjournal.domain.repository.Repository
import javax.inject.Inject

class GetSaveMoodCardUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(
        title: String,
        description: String,
        mood: String,
        pathFile: String,
        date: String

    ) {
        repository.saveMoodCard(
            MoodCardEnity(
                title = title,
                description = description,
                mood = mood,
                pathFile = pathFile,
                date = date
            )
        )
    }
}