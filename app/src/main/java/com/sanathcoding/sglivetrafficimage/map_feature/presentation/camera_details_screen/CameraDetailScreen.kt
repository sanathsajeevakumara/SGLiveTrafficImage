package com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_details_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.sanathcoding.sglivetrafficimage.R
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_details_screen.component.CameraDetailView
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen.MapViewModel

@Composable
fun CameraDetailScreen(
    cameraId: String,
    viewModel: MapViewModel = hiltViewModel(),
) {
    val state = viewModel.mapState

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = { Text(text = "Camera Id $cameraId") }
            )
        }
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(it)) {
            state.camera?.onEach { camera ->
                if (cameraId == camera.cameraId) {
                    CameraDetailView(camera)
                }
            }
        }
    }
}