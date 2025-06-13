package com.example.moodjournal.presentation.home

sealed class HomeEvent {
    data class SearchMood(val nameTitle: String) : HomeEvent()
    object onClick : HomeEvent()
}