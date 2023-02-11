package com.sanathcoding.sglivetrafficimage.map_feature.data.remote.mapper

import com.sanathcoding.sglivetrafficimage.map_feature.data.remote.dto.ImageMetadataDto
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.ImageMetaData

fun ImageMetadataDto.toImageMetaData(): ImageMetaData {
    return ImageMetaData(
        height = height,
        md5 = md5,
        width = width
    )
}