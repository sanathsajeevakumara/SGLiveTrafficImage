package com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_list_screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            state.camera?.forEach {
                items(state.camera.size) { i ->
                    Log.d("cameraList", "list size ${state.camera.size}")
                    TrafficCameraList(
                        camera = state.camera[i],
                        navController

                    )
                }
            }
        }
    }
}