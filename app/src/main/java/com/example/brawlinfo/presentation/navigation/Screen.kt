package com.example.brawlinfo.presentation.navigation

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash_screen")
    object BrawlScreen: Screen("brawl_screen")
    object BrawlerInfoScreen: Screen("brawler_info_screen")
}