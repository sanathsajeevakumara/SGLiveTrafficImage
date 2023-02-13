package com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_list_screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Camera

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CameraDetailPopUp(camera: Camera, onDismiss: () -> Unit) {

    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 4.dp,
            border = BorderStroke(5.dp, Color.Transparent)
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(
                            color = Color(0xFF35898f),
                        shape = RoundedCornerShape(5.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    GlideImage(
                        model = camera.image,
                        contentDescription = "Location Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                    text = "Camera No ${camera.cameraId}",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
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
                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }
}
