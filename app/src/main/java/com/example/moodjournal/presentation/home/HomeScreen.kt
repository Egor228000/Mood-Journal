package com.example.moodjournal.presentation.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onNavigateToAdd: () -> Unit
) {

    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        homeViewModel.effect.collectLatest { effect ->
            when (effect) {
                is HomeEffect.onClickAddMood -> {
                    onNavigateToAdd()
                }
            }
        }
    }


    LazyColumn(
        modifier = Modifier
    ) {
        item {
            TextField(
                value = uiState.titleName,
                onValueChange = { newValue ->
                    homeViewModel.onEvent(HomeEvent.SearchMood(newValue))
                }
            )
            Button(
                onClick = {
                    homeViewModel.onEvent(HomeEvent.onClick)
                }
            ) { }
        }
    }


}