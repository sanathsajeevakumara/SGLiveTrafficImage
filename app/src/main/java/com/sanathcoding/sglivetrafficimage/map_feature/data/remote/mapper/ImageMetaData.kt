package com.sanathcoding.sglivetrafficimage.map_feature.data.remote.mapper

import com.sanathcoding.sglivetrafficimage.map_feature.data.remote.dto.ImageMetadataDto
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.ImageMetadata

fun ImageMetadataDto.toImageMetaData(): ImageMetadata {
    return ImageMetadata(
        height = height,
        md5 = md5,
        width = width
    )
}