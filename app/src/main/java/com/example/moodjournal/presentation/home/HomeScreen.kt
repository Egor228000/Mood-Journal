package com.example.moodjournal.presentation.home

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onNavigateToAdd: () -> Unit,
    onNavigateToWach: (Int) -> Unit,
    onNavigateToEdit: (Int) -> Unit,
) {

    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val focus = LocalFocusManager.current

    LaunchedEffect(Unit) {
        homeViewModel.effect.collectLatest { effect ->
            when (effect) {
                is HomeEffect.onClickAddMood -> {
                    onNavigateToAdd()
                }
                is HomeEffect.navigateWatch -> {
                    onNavigateToWach(effect.id)
                }
                is HomeEffect.navigateEdit -> {
                    onNavigateToEdit(effect.id)
                }


            }
        }
    }
    Column {

        CenterAlignedTopAppBar(
            title = {
                Text("Главаня")
            },
            scrollBehavior = scrollBehavior
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)


        ) {

            OutlinedTextField(
                value = uiState.titleName,
                onValueChange = { newValue ->
                    homeViewModel.onEvent(HomeEvent.SearchMood(newValue))
                },
                modifier = Modifier
                    .fillMaxWidth(1f),
                placeholder = {
                    Text("Поиск")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        focus.clearFocus()
                    }
                )

            )

            LazyColumn(
                contentPadding = PaddingValues(bottom = 50.dp, top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
            ) {
                items(20) { index ->
                    MoodCard(
                        onClick = {homeViewModel.onEvent(HomeEvent.onClickNavigateWatch(index))},
                        onLongClick = {homeViewModel.onEvent( HomeEvent.onDoubleClickNavigateEdit(index))},
                        title = "Заголовок $index",
                        description = "Описаниdsfsdfsdfsdfsdfsdfsdffе $index",
                        "\uD83D\uDE04"
                    )
                }

            }
        }
    }
}


@Composable
fun MoodCard(
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    title: String,
    description: String,
    moodEmoji: String
) {
    OutlinedCard(
        Modifier
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .fillMaxWidth(1f),
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .padding(16.dp)
            ) {

                //title
                Text(
                    title,
                    autoSize = TextAutoSize.StepBased(minFontSize = 18.sp, maxFontSize = 24.sp),
                    fontWeight = FontWeight.Black
                )

                //description

                Text(
                    description,
                    autoSize = TextAutoSize.StepBased(
                        minFontSize = 14.sp,
                        maxFontSize = 20.sp,
                        stepSize = 0.25.sp
                    )
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .padding(end = 32.dp, top = 16.dp)
                    .fillMaxSize(1f)
            ) {
                Text(moodEmoji, fontSize = 50.sp)

            }
        }
    }
}