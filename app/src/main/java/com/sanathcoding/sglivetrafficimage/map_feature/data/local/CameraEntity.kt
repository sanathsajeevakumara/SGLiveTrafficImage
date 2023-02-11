package com.sanathcoding.sglivetrafficimage.map_feature.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.ImageMetaData
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Location

@Entity
data class CameraEntity(
    @PrimaryKey val id: Int? = null,
    val cameraId: String,
    val image: String,
    val imageMetadata: ImageMetaData,
    val location: Location,
    val timestamp: String
)