package com.sanathcoding.sglivetrafficimage.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sanathcoding.sglivetrafficimage.core.navigation.NavigationConst.TEST_ROUTE
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen.NewHome
import com.sanathcoding.sglivetrafficimage.login_feature.presentation.loginScreen.LoginScreen


@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = TEST_ROUTE,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.Home.route) {
            NewHome(navController)
        }
    }
}