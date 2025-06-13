package com.example.moodjournal.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodjournal.domain.usecase.GetNameUseCase
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
    private val getNameUseCase: GetNameUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState


    private val _effect = Channel<HomeEffect>()
    val effect = _effect.receiveAsFlow()


     fun onEvent(event: HomeEvent) {
         viewModelScope.launch {
             when (event) {
                 is HomeEvent.SearchMood -> {
                     _uiState.update { it.copy(titleName = event.nameTitle) }
                 }
                 is HomeEvent.onClick -> {
                     _effect.send(HomeEffect.onClickAddMood)
                 }
             }
         }

    }

}