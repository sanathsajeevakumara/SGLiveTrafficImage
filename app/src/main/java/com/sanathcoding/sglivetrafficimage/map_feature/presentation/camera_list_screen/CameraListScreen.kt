package com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_list_screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sanathcoding.sglivetrafficimage.core.navigation.Screen
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_list_screen.component.TrafficCameraList
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen.MapViewModel

@Composable
fun CameraListScreen(
    navController: NavHostController,
    viewModel: MapViewModel = hiltViewModel(),
) {
    val state = viewModel.mapState
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            state.camera?.forEach {
                items(state.camera.size) { i ->
                    TrafficCameraList(
                        camera = state.camera[i],
                        onItemClick = {
                            navController.navigate(
                                Screen.CameraDetail.createRoute(state.camera[i].cameraId)
                            )
                        }
                    )
                }
            }
        }
    }
}