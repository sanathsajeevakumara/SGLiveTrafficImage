package com.sanathcoding.sglivetrafficimage.map_feature.data.remote.mapper

import com.sanathcoding.sglivetrafficimage.map_feature.data.remote.dto.LocationDto
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Location

fun LocationDto.toLocation(): Location {
    return Location(
        latitude = latitude,
        longitude = longitude
    )
}