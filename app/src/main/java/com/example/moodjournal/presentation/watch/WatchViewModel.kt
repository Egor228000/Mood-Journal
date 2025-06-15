package com.example.moodjournal.presentation.watch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/*
class WatchViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WatchState())
    val uiState: StateFlow<WatchState> = _uiState

    private val _effect = Channel<WatchEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: WatchEvent) {
        when (event) {
            is WatchEvent.ExampleEvent -> {
                // Handle event
            }
        }
    }
}
*/
