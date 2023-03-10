package com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen.component


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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Camera
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_list_screen.component.FavoriteButton
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen.MapViewModel
import com.sanathcoding.sglivetrafficimage.ui.theme.pinkRed

@Composable
fun MarkerContent(camera: Camera) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val bitmapImage = loadUrlImage(url = camera.image)

    Box(
        modifier = Modifier
            .width(screenWidth * 0.75f)
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
            Text(text = "Camera Id: ${camera.cameraId}")
            Spacer(modifier = Modifier.height(16.dp))

            if (bitmapImage != null) {
                Image(
                    bitmapImage.asImageBitmap(),
                    "Image Location",
                    Modifier.fillMaxWidth()
                )
            } else Text("Loading Image...")

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                FavoriteButton(
                    modifier = Modifier.padding(8.dp),
                    color = pinkRed,
                    isFavorite = false
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Time Stamp: ${camera.timestamp}")
            Spacer(modifier = Modifier.height(8.dp))

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