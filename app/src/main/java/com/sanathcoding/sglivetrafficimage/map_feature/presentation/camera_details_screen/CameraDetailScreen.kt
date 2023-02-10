package com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_details_screen


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CameraDetailScreen(
    cameraId: String,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Number is $cameraId",
            fontSize = MaterialTheme.typography.h3.fontSize,
        )
    }
}