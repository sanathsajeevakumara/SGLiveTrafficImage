package com.sanathcoding.sglivetrafficimage.map_feature.data.local.mapper

import com.sanathcoding.sglivetrafficimage.map_feature.data.local.CameraEntity
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Camera

fun CameraEntity.toCamera(): Camera {
    return Camera(
        id = id,
        cameraId = cameraId,
        image = image,
        imageMetadata = imageMetadata,
        location = location,
        timestamp = timestamp
    )
}

fun Camera.toCameraEntity(): CameraEntity {
    return CameraEntity(
        id = id,
        cameraId = cameraId,
        image = image,
        imageMetadata = imageMetadata,
        location = location,
        timestamp = timestamp
    )
}