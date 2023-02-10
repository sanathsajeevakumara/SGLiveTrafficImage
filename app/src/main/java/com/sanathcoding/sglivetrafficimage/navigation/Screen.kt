package com.sanathcoding.sglivetrafficimage.navigation

sealed class Screen(val route: String) {
    object Login: Screen("login")
    object Home: Screen("home")
    object CameraDetail: Screen("camera_detail/{cameraId}") {
        fun createRoute(cameraId: String) = "camera_detail/$cameraId"
    }
}