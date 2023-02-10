package com.sanathcoding.sglivetrafficimage.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sanathcoding.sglivetrafficimage.navigation.Graph.LOGIN_SCREEN
import com.sanathcoding.sglivetrafficimage.navigation.destination.LoginScreenDestination
import com.sanathcoding.sglivetrafficimage.presentation.loginScreen.LoginScreen


fun NavGraphBuilder.loginNavGraph(navHostController: NavHostController) {
    navigation(
        route = LOGIN_SCREEN,
        startDestination = LoginScreenDestination.Login.route
    ) {
        composable(route = LoginScreenDestination.Login.route) {
            LoginScreen(navController = navHostController)
        }
    }
}