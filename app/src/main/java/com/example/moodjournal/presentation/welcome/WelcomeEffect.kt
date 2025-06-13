package com.example.moodjournal.presentation.welcome

sealed class WelcomeEffect {
    object NavigateHomeScreen : WelcomeEffect()
    object TriggerHapticFeedback : WelcomeEffect() // 👈 добавлено
}