package com.github.mcgalanes.happtech.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.mcgalanes.happtech.core.design.HapptechTheme
import com.github.mcgalanes.happtech.core.navigation.NavScreen
import com.github.mcgalanes.happtech.feature.museumcollection.detail.MuseumDetailScreen
import com.github.mcgalanes.happtech.feature.museumcollection.list.MuseumListScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            HapptechTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = NavScreen.MuseumList,
                ) {
                    composable<NavScreen.MuseumList> {
                        MuseumListScreen(
                            modifier = Modifier.fillMaxSize(),
                            navToDetail = { objectNumber ->
                                navController.navigate(NavScreen.MuseumDetail(objectNumber))
                            },
                        )
                    }

                    composable<NavScreen.MuseumDetail> {
                        MuseumDetailScreen(
                            modifier = Modifier.fillMaxSize(),
                            onNavUp = { navController.navigateUp() },
                        )
                    }
                }
            }
        }
    }
}
