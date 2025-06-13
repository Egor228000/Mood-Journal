package com.example.moodjournal.domain.usecase

import com.example.moodjournal.data.local.entity.WelcomeEntity
import com.example.moodjournal.domain.repository.Repository
import javax.inject.Inject

class GetSaveNameUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Int, name: String) {
        repository.saveWelcomeName(WelcomeEntity(id = id, name = name))
    }
}
