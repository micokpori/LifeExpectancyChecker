package com.rfcreations.lifeexpectancychecker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rfcreations.lifeexpectancychecker.ui.screens.home_screen.HomeScreen
import com.rfcreations.lifeexpectancychecker.ui.screens.splash_screen.SplashScreen
import com.rfcreations.lifeexpectancychecker.ui.viewmodel.MyViewModel

@Composable
fun NavigationSetup(myViewModel: MyViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
        //startDestination = Screens.SplashScreen.route
    ){
        composable(route = Screens.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(route = Screens.HomeScreen.route){
            HomeScreen(myViewModel)
        }
    }
}