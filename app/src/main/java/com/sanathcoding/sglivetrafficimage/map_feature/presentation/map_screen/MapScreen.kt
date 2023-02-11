package com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.sanathcoding.sglivetrafficimage.core.data.remote.dto.Camera

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
}

@Composable
fun MarkerContent(camera: Camera) {

    //Save image as bitmap
    var bitmap by remember { mutableStateOf<Bitmap?>(null)}

    Glide.with(LocalContext.current).asBitmap()
        .load(camera.image)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })

    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colors.onPrimary,
                shape = RoundedCornerShape(5.dp)
            )
        ,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Time Stamp: ${camera.timestamp}")

//            Load bitmap image to Image composable
            if (bitmap != null )
                Image(
                    bitmap!!.asImageBitmap(),
                    "Image Location",
                    Modifier.size(150.dp)
                )
            else
                Text("Loading Image...")

            Log.d("MapScreen", "Location Image url : ${camera.image}")

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