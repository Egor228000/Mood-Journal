package com.example.moodjournal.presentation.welcome

import android.os.Message


// то что отображается на экране
data class WelcomeUiState(
    val name: String = "",
    val errorMessage: String = ""
)
