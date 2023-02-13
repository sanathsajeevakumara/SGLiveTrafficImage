package com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_details_screen.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Camera


@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun CameraDetailView(camera: Camera) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        GlideImage(
            model = camera.image,
            contentDescription = "Location Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Time Stamp: ${camera.timestamp}")
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Location",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Latitude: ${camera.location.latitude}")
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "Longitude: ${camera.location.longitude}")
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Image MetaData",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Height: ${camera.imageMetadata.height}")
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Width: ${camera.imageMetadata.width}")
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Md5: ${camera.imageMetadata.md5}")
    }
}