package com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen.component.MarkerContent

@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel(),
) {

    val state = viewModel.mapState

    val scaffoldState = rememberScaffoldState()
    val mapUiSetting = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }

    val targetSingapore = LatLng(1.3521, 103.8198)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(targetSingapore, 10f)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(MapEvent.ToggleFallOutMap)
            }) {
                Icon(
                    imageVector = if (viewModel.mapState.isFallOutMap) {
                        Icons.Default.DarkMode
                    } else Icons.Default.LightMode, contentDescription = "Toggle Fallout map "
                )
            }
        }
    ) {
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            properties = viewModel.mapState.properties,
            uiSettings = mapUiSetting,
            cameraPositionState = cameraPositionState
        ) {

            state.camera?.forEach { camera ->
                MarkerInfoWindow(
                    state = MarkerState(LatLng(camera.location.latitude, camera.location.longitude)),
                    title = "Location (${camera.location.latitude}, ${camera.location.longitude})",
                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_RED
                    ),
                ) {
                    MarkerContent(camera = camera)
                }
            }
        }
    }
}

