package com.example.moodjournal.domain.usecase.repository.welcome

import com.example.moodjournal.data.local.entity.welcome.WelcomeEntity
import javax.inject.Inject

class GetWelcomeNameUseCase @Inject constructor(
    private val repository: WelcomeRepository
) {
    suspend operator fun invoke(): String = repository.getWelcomeName()

}

class GetSaveWelcomeNameUseCase @Inject constructor(
    private val repository: WelcomeRepository
) {
    suspend operator fun invoke(id: Int, name: String) {
        repository.saveWelcomeName(WelcomeEntity(id = id, name = name))
    }
}