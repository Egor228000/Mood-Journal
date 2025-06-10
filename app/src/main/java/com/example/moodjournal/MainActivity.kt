package com.example.moodjournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.moodjournal.nav_root.HomeScreenNavKey
import com.example.moodjournal.nav_root.NavigationRoot
import com.example.moodjournal.nav_root.WelcomeScreenNavKey
import com.example.moodjournal.ui.theme.MoodJournalTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val backStack = rememberNavBackStack(HomeScreenNavKey)

            MoodJournalTheme {
                Scaffold(
                    modifier = Modifier,
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

