package com.example.moodjournal.presentation.welcome

import android.os.Message

sealed class WelcomeEffect {
    object NavigateHomeScreen : WelcomeEffect()
}