package com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_list_screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sanathcoding.sglivetrafficimage.core.navigation.Screen
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Camera
import com.sanathcoding.sglivetrafficimage.ui.theme.pinkRed

@Composable
fun TrafficCameraList(
    camera: Camera,
    navController: NavHostController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Camera ${camera.cameraId}",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.clickable {
                navController.navigate(
                    Screen.CameraDetail.createRoute(camera.cameraId)
                )
            }
        )
        FavoriteButton(
            modifier = Modifier.padding(8.dp),
            color = pinkRed
        )
    }
}