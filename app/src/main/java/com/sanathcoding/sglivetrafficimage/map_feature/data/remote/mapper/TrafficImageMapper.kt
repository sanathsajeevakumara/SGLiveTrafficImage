package com.sanathcoding.sglivetrafficimage.map_feature.data.remote.mapper

import com.sanathcoding.sglivetrafficimage.map_feature.data.remote.dto.TrafficImageDto
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.TrafficImage


fun TrafficImageDto.toTrafficImage(): TrafficImage {
    return TrafficImage(
        apiInfo = apiInfo.toApiInfo(),
        items = items.map { it.toItem() }
    )
}