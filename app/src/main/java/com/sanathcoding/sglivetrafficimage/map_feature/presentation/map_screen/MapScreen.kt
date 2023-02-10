package com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.sanathcoding.sglivetrafficimage.core.data.remote.dto.Camera
import com.sanathcoding.sglivetrafficimage.core.data.remote.dto.Location

@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel(),
) {

    val state = viewModel.mapState

    val mapUiSetting = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }

    val targetSingapore = LatLng(1.3521, 103.8198)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(targetSingapore, 10f)
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxSize(),
        properties = viewModel.mapState.properties,
        uiSettings = mapUiSetting,
        cameraPositionState = cameraPositionState
    ) {



        state.trafficImage?.let {
            it.items.map { item ->
                item.cameras.map { camera ->
                    Marker(
                        position = LatLng(camera.location.latitude, camera.location.longitude),
                        title = "Location (${camera.location.latitude}, ${camera.location.longitude})",
                        onClick = { maker ->
                            maker.showInfoWindow()
                            true
                        },
                        icon = BitmapDescriptorFactory.defaultMarker(
                            BitmapDescriptorFactory.HUE_RED
                        )
                    )
                }
            }
        }
    }
}
@Composable
fun MarkerContent(camera: Camera) {
    Column(
        modifier = Modifier
            .height(180.dp)
            .width(180.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Time Stamp: ${camera.timestamp}")
            Image(
                painter = rememberAsyncImagePainter(camera.image),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )

            Text(text = "Location:")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Latitude: ${camera.location.latitude}")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Longitude: ${camera.location.longitude}")
            Text(text = "Camera Id: ${camera.cameraId}")

            Text(text = "Image MetaData:")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Height: ${camera.imageMetadata.height}")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Width: ${camera.imageMetadata.width}")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Md5: ${camera.imageMetadata.md5}")
        }
    }
}