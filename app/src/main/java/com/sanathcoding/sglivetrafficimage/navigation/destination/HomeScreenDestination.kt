package com.sanathcoding.sglivetrafficimage.navigation.destination

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Map
import androidx.compose.ui.graphics.vector.ImageVector

sealed class HomeScreenDestination(
    val name: String,
    val route: String,
    val icon: ImageVector
) {
    object MapScreen: HomeScreenDestination(
        name = "Map",
        route = "Map",
        icon = Icons.Default.Map,
    )

    object CameraListScreen: HomeScreenDestination(
        name = "Camera List",
        route = "List",
        icon = Icons.Default.List,
    )
}