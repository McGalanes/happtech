package com.github.mcgalanes.happtech.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.github.mcgalanes.happtech.core.design.HapptechTheme
import com.github.mcgalanes.happtech.core.navigation.MuseumDetailScreen
import com.github.mcgalanes.happtech.core.navigation.MuseumListScreen
import com.github.mcgalanes.happtech.feature.museumcollection.detail.MuseumDetailScreen
import com.github.mcgalanes.happtech.feature.museumcollection.list.MuseumListScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            HapptechTheme {
                val backStack = rememberNavBackStack(MuseumListScreen)

                NavDisplay(
                    modifier = Modifier.fillMaxSize(),
                    backStack = backStack,
                    entryDecorators = listOf(
                        rememberSavedStateNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator(),
                        rememberSceneSetupNavEntryDecorator(),
                    ),
                ) { navKey ->
                    when (navKey) {
                        is MuseumListScreen -> {
                            NavEntry(key = navKey) {
                                MuseumListScreen(
                                    modifier = Modifier.fillMaxSize(),
                                    navToDetail = { objectNumber ->
                                        backStack.add(MuseumDetailScreen(objectNumber))
                                    },
                                )
                            }
                        }

                        is MuseumDetailScreen -> {
                            NavEntry(key = navKey) {
                                MuseumDetailScreen(
                                    modifier = Modifier.fillMaxSize(),
                                    viewModel = koinViewModel {
                                        parametersOf(navKey.objectNumber)
                                    },
                                    onNavUp = { backStack.pop() },
                                )
                            }
                        }

                        else -> throw IllegalArgumentException("Unknown NavKey: $navKey")
                    }
                }
            }
        }
    }
}

private fun NavBackStack.pop() = removeAt(lastIndex)
