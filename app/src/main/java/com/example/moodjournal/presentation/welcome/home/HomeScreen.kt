package com.example.moodjournal.presentation.welcome.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        homeViewModel.onEvent(HomeEvent.onSumbitClicked)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Привет, ${uiState.name}",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}