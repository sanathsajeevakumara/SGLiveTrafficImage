package com.sanathcoding.sglivetrafficimage.map_feature.presentation.camera_list_screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Camera

@Composable
fun TrafficCameraList(
    camera: Camera,
    onItemClick: (Camera) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(camera) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Camera ${camera.cameraId}",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )
//        Text(
//            text = if(coin.isActive) "active" else "inactive",
//            color = if(coin.isActive) Color.Green else Color.Red,
//            fontStyle = FontStyle.Italic,
//            textAlign = TextAlign.End,
//            style = MaterialTheme.typography.body2,
//            modifier = Modifier.align(CenterVertically)
//        )
    }
}