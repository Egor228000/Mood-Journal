package com.example.moodjournal.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodjournal.domain.usecase.GetSaveNameUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
     private val getSaveNameUseCase: GetSaveNameUseCase
): ViewModel() {

     private val _uiState = MutableStateFlow(WelcomeUiState())
     val uiState: StateFlow<WelcomeUiState> = _uiState

     private val _effect = Channel<WelcomeEffect>()
     val effect = _effect.receiveAsFlow()

     fun onEvent(event: WelcomeEvent) {
          when (event) {
               is WelcomeEvent.OnNameChanged -> {
                    _uiState.update { it.copy(name = event.name) }
               }
               is WelcomeEvent.onSumbitClicked -> {
                    viewModelScope.launch {
                         val name = uiState.value.name.trim()
                         if (name.isBlank()) {
                              _uiState.update { it.copy(errorMessage = "Имя не может быть пустым") }
                              _effect.send(WelcomeEffect.TriggerHapticFeedback)
                         } else {
                              _uiState.update { it.copy(errorMessage = "") } // сбрасываем ошибку
                              getSaveNameUseCase.invoke(id = 1, name = name)
                              _effect.send(WelcomeEffect.NavigateHomeScreen)
                         }
                    }
               }
          }
     }
}