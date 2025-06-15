package com.example.moodjournal

import android.icu.text.Transliterator
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults.contentWindowInsets
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.moodjournal.core.navigation.HomeScreenNavKey
import com.example.moodjournal.core.navigation.NavigationRoot
import com.example.moodjournal.core.navigation.SettingScreenNavKey
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
            val backStack = rememberNavBackStack(HomeScreenNavKey)
            MoodJournalTheme {
                    Scaffold(
                        floatingActionButton =  {
                            ExtendedFloatingActionButton(
                                onClick = {}
                            ) {
                                Icon(painter = painterResource(R.drawable.rounded_add_reaction_24), null)
                            }
                        },
                        floatingActionButtonPosition =  FabPosition.End,
                        contentWindowInsets = WindowInsets(0.dp),
                        topBar = {
                            when (backStack.lastOrNull()) {

                                is WelcomeScreenNavKey -> {}
                                is HomeScreenNavKey -> {}
                                else -> {
                                    CenterAlignedTopAppBar(
                                        title = {
                                            Text(
                                                text = when (backStack.lastOrNull()) {
                                                    is HomeScreenNavKey -> "Главаня"
                                                    is SettingScreenNavKey -> "Настройки"
                                                    else -> {
                                                        ""
                                                    }
                                                }

                                            )
                                        },
                                        actions = {
                                            IconButton(onClick = { /* Handle settings */ }) {
                                            }
                                        },
                                        colors = TopAppBarDefaults.topAppBarColors(
                                            containerColor = Color.White,
                                            titleContentColor = Color.Black
                                        )
                                    )
                                }
                            }


                        },
                        bottomBar = {
                            when (backStack.lastOrNull()) {
                                is SettingScreenNavKey -> {
                                    BottomAppBar(
                                        containerColor = Color.Cyan,
                                        contentColor = Color.Black,
                                        modifier = Modifier
                                    ) {
                                        NavigationBarItem(
                                            selected = true,
                                            onClick = {},
                                            label = { Text("Сатистика") },
                                            icon = {
                                                Icon(
                                                    painter = painterResource(R.drawable.rounded_add_reaction_24),
                                                    null
                                                )

                                            }
                                        )
                                        NavigationBarItem(
                                            selected = false,
                                            onClick = {},
                                            label = { Text("Настройки") },
                                            icon = {
                                                Icon(
                                                    painter = painterResource(R.drawable.rounded_image_arrow_up_24),
                                                    null
                                                )


                                            }
                                        )

                                    }
                                }
                            }

                        },
                        content = { innerPadding ->
                          NavigationRoot(backStack, innerPadding)
                        }
                    )
            }
        }
    }
}


