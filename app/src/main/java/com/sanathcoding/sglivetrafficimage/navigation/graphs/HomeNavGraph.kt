package com.sanathcoding.sglivetrafficimage.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sanathcoding.sglivetrafficimage.navigation.Graph
import com.sanathcoding.sglivetrafficimage.navigation.destination.HomeScreenDestination
import com.sanathcoding.sglivetrafficimage.presentation.home_screen.screen.CameraListScreen
import com.sanathcoding.sglivetrafficimage.presentation.home_screen.screen.MapScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeScreenDestination.MapScreen.route,
        route = Graph.HOME_SCREEN
    ) {
        composable(route = HomeScreenDestination.MapScreen.route) {
            MapScreen()
        }
        composable(route = HomeScreenDestination.CameraListScreen.route) {
            CameraListScreen(navController)
        }
        detailNavGraph(navController = navController)
    }
}