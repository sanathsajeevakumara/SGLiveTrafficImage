package com.sanathcoding.sglivetrafficimage.map_feature.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CameraDto(
    @SerializedName("camera_id")
    val cameraId: String,
    val image: String,
    @SerializedName("image_metadata")
    val imageMetadata: ImageMetadataDto,
    val location: LocationDto,
    val timestamp: String
)