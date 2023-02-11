package com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen.component

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Camera

@Composable
fun MarkerContent(camera: Camera) {

    val bitmapImage = loadUrlImage(url = camera.image)

    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colors.onPrimary,
                shape = RoundedCornerShape(5.dp)
            ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Time Stamp: ${camera.timestamp}")
            Spacer(modifier = Modifier.height(16.dp))

            if (bitmapImage != null) {
                Image(
                    bitmapImage.asImageBitmap(),
                    "Image Location",
                    Modifier.size(150.dp)
                )
            } else Text("Loading Image...")

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Location",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Latitude: ${camera.location.latitude}")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Longitude: ${camera.location.longitude}")

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Camera Id: ${camera.cameraId}")
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
}