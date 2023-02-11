package com.sanathcoding.sglivetrafficimage.map_feature.data.remote.mapper

import com.sanathcoding.sglivetrafficimage.map_feature.data.remote.dto.ApiInfoDto
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.ApiInfo

fun ApiInfoDto.toApiInfo(): ApiInfo {
    return ApiInfo(
        status = status
    )
}