package com.example.moodjournal.presentation.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/*
class EditViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(EditUi())
    val uiState: StateFlow<EditUiState> = _uiState

    private val _effect = Channel<EditEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: EditEvent) {
        when (event) {
            is EditEvent.ExampleEvent -> {
                // Handle event
            }
        }
    }
}
*/
