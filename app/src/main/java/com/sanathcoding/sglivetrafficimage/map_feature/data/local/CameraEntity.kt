package com.sanathcoding.sglivetrafficimage.map_feature.data.local

import androidx.room.Entity
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.ImageMetadata
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Location

@Entity
data class CameraEntity(
    val cameraId: String,
    val image: String,
    val imageMetadata: ImageMetadata,
    val location: Location,
    val timestamp: String
)