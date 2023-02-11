package com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_details_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_details_screen.component.CameraDetailView
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen.MapViewModel

@Composable
fun CameraDetailScreen(
    cameraId: String,
    viewModel: MapViewModel = hiltViewModel(),
) {
    val state = viewModel.mapState

    state.camera?.onEach { camera ->
        if (cameraId == camera.cameraId) {
            CameraDetailView(camera)
        }
    }
}