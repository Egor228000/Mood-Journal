package com.example.moodjournal.presentation.home

sealed class HomeEffect {
    object onClickAddMood : HomeEffect()
    data class navigateWatch(val id: Int) : HomeEffect()
    data class navigateEdit(val id: Int) : HomeEffect()
}