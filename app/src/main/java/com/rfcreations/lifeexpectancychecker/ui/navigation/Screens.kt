package com.rfcreations.lifeexpectancychecker.ui.navigation

sealed class Screens(val route: String) {
    data object HomeScreen: Screens("home_screen")
    data object SplashScreen: Screens("splash_screen")
}
