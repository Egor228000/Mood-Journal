package com.example.moodjournal

import android.content.res.Resources
import android.graphics.Color.TRANSPARENT
import android.graphics.Color.toArgb
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.moodjournal.core.navigation.HomeScreenNavKey
import com.example.moodjournal.core.navigation.NavigationRoot
import com.example.moodjournal.core.navigation.WelcomeScreenNavKey
import com.example.moodjournal.ui.theme.MoodJournalTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val backStack = rememberNavBackStack(WelcomeScreenNavKey)

            MoodJournalTheme {
                Scaffold(
                    contentWindowInsets = WindowInsets(),
                    topBar = {
                        when(backStack.lastOrNull()) {
                            WelcomeScreenNavKey -> {}
                            else -> {
                                CenterAlignedTopAppBar(
                                    title = {Text("Main")}
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavigationRoot(backStack, innerPadding)

                }
            }
        }
    }
}

