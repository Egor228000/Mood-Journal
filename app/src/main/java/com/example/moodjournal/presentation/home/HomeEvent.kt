package com.example.moodjournal.presentation.home

sealed class HomeEvent {
    data class SearchMood(val nameTitle: String) : HomeEvent()
    object onClick : HomeEvent()
    data class onClickNavigateWatch(val id: Int) : HomeEvent()
    data class onDoubleClickNavigateEdit(val id: Int) : HomeEvent()
}