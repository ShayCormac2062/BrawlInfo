package com.example.brawlinfo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.brawlinfo.presentation.screen.BrawlScreen
import com.example.brawlinfo.presentation.screen.BrawlerInfoScreen
import com.example.brawlinfo.presentation.screen.SplashScreen
import com.example.brawlinfo.presentation.viewmodel.BrawlViewModel

@Composable
fun SetupNavGraph(
    brawlViewModel: BrawlViewModel = viewModel(),
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(
            route = Screen.SplashScreen.route
        ) {
            SplashScreen(navController)
        }
        composable(
            route = Screen.BrawlScreen.route
        ) {
            BrawlScreen(
                brawlViewModel,
                navController
            )
        }
        composable(
            route = Screen.BrawlerInfoScreen.route,
        ) {
            BrawlerInfoScreen(brawlViewModel)
        }
    }
}