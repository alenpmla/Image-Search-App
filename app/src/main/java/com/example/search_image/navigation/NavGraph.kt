package com.example.search_image.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.search_image.presentation.ui.screens.HomeScreen
import com.example.search_image.presentation.ui.screens.ImageDetailsScreen

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("index") {
                type = NavType.IntType
            })
        ) {
            val selectedIndex = it.arguments?.getInt("index")
            ImageDetailsScreen(navController = navController, selectedIndex)
        }

    }
}