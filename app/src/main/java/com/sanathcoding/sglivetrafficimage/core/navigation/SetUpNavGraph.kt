package com.sanathcoding.sglivetrafficimage.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_details_screen.CameraDetailScreen
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.home_screen.NewHome
import com.sanathcoding.sglivetrafficimage.login_feature.presentation.loginScreen.LoginScreen

const val TEST_ROUTE = "test_route"

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
        composable(Screen.CameraDetail.route) { naveBackStackEntry ->
            val cameraId = naveBackStackEntry.arguments?.getString("cameraId")
            cameraId?.let {
                CameraDetailScreen(it)
            }
        }
    }
}