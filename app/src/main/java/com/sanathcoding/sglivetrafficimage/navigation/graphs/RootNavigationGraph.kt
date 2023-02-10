package com.sanathcoding.sglivetrafficimage.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sanathcoding.sglivetrafficimage.navigation.Graph
import com.sanathcoding.sglivetrafficimage.navigation.Graph.HOME_SCREEN
import com.sanathcoding.sglivetrafficimage.navigation.Graph.LOGIN_SCREEN
import com.sanathcoding.sglivetrafficimage.presentation.home_screen.HomeScreen

@Composable
fun RootNavigationGraph(navHostController: NavHostController) {
    
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = LOGIN_SCREEN
    ) {
        loginNavGraph(navHostController = navHostController)
        composable(route = HOME_SCREEN) {
            HomeScreen()
        }
    }

}