package com.sanathcoding.sglivetrafficimage.map_feature.data.remote.mapper

import com.sanathcoding.sglivetrafficimage.map_feature.data.remote.dto.CameraDto
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Camera

fun CameraDto.toCamera(): Camera {
    return Camera(
        cameraId = cameraId,
        image = image,
        imageMetadata = imageMetadata.toImageMetaData(),
        location = location.toLocation(),
        timestamp = timestamp
    )
}