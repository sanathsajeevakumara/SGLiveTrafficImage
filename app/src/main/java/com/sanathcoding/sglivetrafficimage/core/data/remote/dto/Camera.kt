package com.sanathcoding.sglivetrafficimage.core.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Camera(
    @SerializedName("camera_id")
    val cameraId: String,
    val image: String,
    @SerializedName("image_metadata")
    val imageMetadata: ImageMetadata,
    val location: Location,
    val timestamp: String
)