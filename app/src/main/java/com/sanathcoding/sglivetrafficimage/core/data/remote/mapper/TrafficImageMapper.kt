package com.sanathcoding.sglivetrafficimage.core.data.remote.mapper

import com.sanathcoding.sglivetrafficimage.core.data.remote.dto.TrafficImageDto
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.TrafficImage

fun TrafficImageDto.toTrafficImage(): TrafficImage {
    return TrafficImage(
        apiInfo = apiInfo,
        items = items
    )
}