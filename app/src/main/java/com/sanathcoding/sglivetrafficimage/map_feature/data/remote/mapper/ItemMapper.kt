package com.sanathcoding.sglivetrafficimage.map_feature.data.remote.mapper

import com.sanathcoding.sglivetrafficimage.map_feature.data.remote.dto.ItemDto
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Item

fun ItemDto.toItem(): Item {
    return Item(
        cameras = cameras.map { it.toCamera() },
        timestamp = timestamp
    )
}