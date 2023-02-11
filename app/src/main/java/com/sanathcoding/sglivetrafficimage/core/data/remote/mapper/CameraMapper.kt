package com.sanathcoding.sglivetrafficimage.core.data.remote.mapper

import com.sanathcoding.sglivetrafficimage.core.data.remote.dto.CameraDto
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