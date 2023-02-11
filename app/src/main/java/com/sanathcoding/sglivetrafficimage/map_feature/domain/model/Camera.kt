package com.sanathcoding.sglivetrafficimage.map_feature.domain.model



data class Camera(
    val cameraId: String,
    val image: String,
    val imageMetadata: ImageMetadata,
    val location: Location,
    val timestamp: String
)
