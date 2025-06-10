package com.example.moodjournal.presentation.welcome.home

import android.R.attr.name
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Insert
import com.example.moodjournal.domain.usecase.repository.welcome.GetSaveWelcomeNameUseCase
import com.example.moodjournal.domain.usecase.repository.welcome.GetWelcomeNameUseCase
import com.example.moodjournal.presentation.welcome.welcome.WelcomeEffect
import com.example.moodjournal.presentation.welcome.welcome.WelcomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWelcomeNameUseCase: GetWelcomeNameUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

   private val _effect = Channel<HomeEffect>()
    val effect = _effect.receiveAsFlow()

     fun onEvent(event: HomeEvent) {
         viewModelScope.launch {
             when (event) {
                 is HomeEvent.onSumbitClicked -> {
                     var name = getWelcomeNameUseCase.invoke()
                     _uiState.update { it.copy(name = name) }
                 }
             }
         }

    }

}