package com.example.moodjournal.presentation.welcome

sealed class WelcomeEvent {
    data class OnNameChanged(val name: String) : WelcomeEvent()
    object onSumbitClicked : WelcomeEvent()
}