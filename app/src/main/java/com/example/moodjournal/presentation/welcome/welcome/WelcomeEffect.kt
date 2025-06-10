package com.example.moodjournal.presentation.welcome.welcome

sealed class WelcomeEffect {
    object SaveName : WelcomeEffect()
    object NavigateHomeScreen : WelcomeEffect()
}