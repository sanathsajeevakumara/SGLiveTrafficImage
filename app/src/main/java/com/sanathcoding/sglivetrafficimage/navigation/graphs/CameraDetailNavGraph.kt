package com.sanathcoding.sglivetrafficimage.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sanathcoding.sglivetrafficimage.navigation.Graph
import com.sanathcoding.sglivetrafficimage.navigation.destination.CameraDetailScreenDestination
import com.sanathcoding.sglivetrafficimage.presentation.camera_details_screen.CameraDetailScreen

fun NavGraphBuilder.detailNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.CAMERA_DETAIL_SCREEN,
        startDestination = CameraDetailScreenDestination.CameraDetailScreen.route
    ) {
        composable(route = CameraDetailScreenDestination.CameraDetailScreen.route) { navBackStackEntry ->
            val cameraId = navBackStackEntry.arguments?.getString("cameraId")
            cameraId?.let {
                CameraDetailScreen(it)
            }
        }
    }
}