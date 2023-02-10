package com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.sanathcoding.sglivetrafficimage.core.navigation.Screen

@Composable
fun CameraListScreen(
    navController: NavHostController,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Camera List Screen",
            fontSize = MaterialTheme.typography.h3.fontSize,
            modifier = Modifier.clickable {
                navController.navigate(Screen.CameraDetail.createRoute("5"))
            }
        )
    }
}