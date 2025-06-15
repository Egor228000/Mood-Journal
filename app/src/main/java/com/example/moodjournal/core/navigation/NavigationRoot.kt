package com.example.moodjournal.core.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.navEntryDecorator
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.moodjournal.core.navigation.AddScreenNavKey
import com.example.moodjournal.presentation.add.AddScreen
import com.example.moodjournal.presentation.edit.EditScreen
import com.example.moodjournal.presentation.welcome.WelcomeScreen
import com.example.moodjournal.presentation.welcome.WelcomeViewModel
import com.example.moodjournal.presentation.home.HomeScreen
import com.example.moodjournal.presentation.home.HomeViewModel
import com.example.moodjournal.presentation.setting.SettingScreen
import com.example.moodjournal.presentation.watch.WatchScreen
import kotlinx.serialization.Serializable

@Serializable
data object WelcomeScreenNavKey: NavKey

@Serializable
data object HomeScreenNavKey: NavKey

@Serializable
data object AddScreenNavKey: NavKey

@Serializable
data object SettingScreenNavKey: NavKey

@Serializable
data object StatisticsScreenNavKey: NavKey

@Serializable
data class WatchScreenNavKey(val id: Int = 0): NavKey

@Serializable
data class EditScreenNavKey(val id: Int = 0): NavKey






@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavigationRoot(
    backStack: NavBackStack,
    paddingValues: PaddingValues
) {
    val localNavSharedTransitionScope: ProvidableCompositionLocal<SharedTransitionScope> =
        compositionLocalOf {
            throw IllegalStateException(
                "Unexpected access to LocalNavSharedTransitionScope. You must provide a " +
                        "SharedTransitionScope from a call to SharedTransitionLayout() or " +
                        "SharedTransitionScope()"
            )
        }

    val sharedEntryInSceneNavEntryDecorator = navEntryDecorator { entry ->
        with(localNavSharedTransitionScope.current) {
            Box(
                Modifier
                    .sharedElement(
                        rememberSharedContentState(entry.key),
                        animatedVisibilityScope = LocalNavAnimatedContentScope.current,
                    ),
            ) {
                entry.content(entry.key)
            }
        }
    }
    val twoPaneStrategy = remember { TwoPaneSceneStrategy<Any>() }
    SharedTransitionLayout {
        CompositionLocalProvider(localNavSharedTransitionScope provides this) {
            NavDisplay(
                modifier = Modifier.padding(paddingValues),
                backStack = backStack,
                onBack = { keysToRemove -> repeat(keysToRemove) { backStack.removeLastOrNull() } },
                entryDecorators = listOf(
                    sharedEntryInSceneNavEntryDecorator,
                    rememberSceneSetupNavEntryDecorator(),
                    rememberSavedStateNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator()
                ),
                sceneStrategy = twoPaneStrategy,
                entryProvider = entryProvider {
                    entry<WelcomeScreenNavKey> {
                        val welcomeViewModel: WelcomeViewModel = hiltViewModel()
                        WelcomeScreen(
                            welcomeViewModel,
                            onNavigateToHome = {backStack.add(HomeScreenNavKey)}
                        )
                    }
                    entry<HomeScreenNavKey> { id ->
                        val homeViewModel: HomeViewModel = hiltViewModel()
                        HomeScreen(
                            homeViewModel,
                            onNavigateToAdd =   { backStack.add(AddScreenNavKey) },
                            onNavigateToWach = {   id ->
                                backStack.add(
                                    WatchScreenNavKey(id = id)   // ← вытаскиваем Int из navKey
                                )
                            },
                            onNavigateToEdit =  {id ->
                                backStack.add(
                                    EditScreenNavKey(id = id)    // ← аналогично для EditScreenNavKey
                                )
                            }
                        )
                    }

                    entry<WatchScreenNavKey> { id ->
                        WatchScreen(id.id)
                    }
                    entry<EditScreenNavKey> {id ->
                        EditScreen(id.id)
                    }

                }
            )

        }
    }
}