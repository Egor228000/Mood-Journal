package com.example.moodjournal.presentation.welcome

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.moodjournal.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WelcomeScreen(
    welcomeViewModel: WelcomeViewModel,
    onNavigateToHome: () -> Unit
) {



    val uiState by welcomeViewModel.uiState.collectAsStateWithLifecycle()
    val haptic = LocalHapticFeedback.current
    LaunchedEffect(Unit) {
        welcomeViewModel.effect.collectLatest { effect ->
            when (effect) {
                is WelcomeEffect.NavigateHomeScreen -> {
                    onNavigateToHome()
                }

                is WelcomeEffect.TriggerHapticFeedback -> {
                    haptic.performHapticFeedback(HapticFeedbackType.Confirm) // 👈 здесь вибрация
                }
            }
        }
    }
    Box() {

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(64.dp)
        ) {
            item {
                Image(
                    painter = painterResource(R.drawable.welcome_image),
                    null,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(400.dp),
                    contentScale = ContentScale.Crop
                )
            }
            item {

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontFamily = FontFamily.Monospace,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Добро пожаловать в ")

                        }
                        withStyle(
                            style = SpanStyle(
                                fontFamily = FontFamily.Monospace,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W900
                            )
                        ) {
                            append("Mood Journal")
                        }
                    },
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )

            }
            item {
                Text(
                    "Отслеживайте и записчывайте свое настроение, " +
                            "получайте информацию и улучшайте свое " +
                            "эмоциональное состояние",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontSize = 17.sp,
                    ),
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)

                )
            }
            item {
                OutlinedTextField(
                    value = uiState.name,
                    onValueChange = { newValue ->
                        welcomeViewModel.onEvent(WelcomeEvent.OnNameChanged(newValue))
                    },
                    isError = uiState.errorMessage.isNotEmpty(),
                    label = { Text("Введите имя") },
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(horizontal = 16.dp),
                    supportingText = {
                        if (uiState.errorMessage.isNotEmpty()) {
                            Text(
                                text = uiState.errorMessage,
                                color = MaterialTheme.colorScheme.error,
                                fontSize = 14.sp
                            )
                        }
                    },
                    textStyle = TextStyle(
                        fontSize = 18.sp
                    )
                )

            }


        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize(1f)
        ) {
            Button(
                onClick = {
                    welcomeViewModel.onEvent(WelcomeEvent.onSumbitClicked)
                },
                modifier = Modifier
                    .navigationBarsPadding()
                    .height(50.dp)
                    .fillMaxWidth(1f),
                shape = RoundedCornerShape(0.dp)

            ) {
                Text("Начать пользоваться")
            }

        }
    }
}