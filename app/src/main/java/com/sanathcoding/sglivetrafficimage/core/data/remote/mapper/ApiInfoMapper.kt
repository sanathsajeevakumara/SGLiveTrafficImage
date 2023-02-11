package com.sanathcoding.sglivetrafficimage.core.data.remote.mapper

import com.sanathcoding.sglivetrafficimage.core.data.remote.dto.ApiInfoDto
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.ApiInfo

fun ApiInfoDto.toApiInfo(): ApiInfo {
    return ApiInfo(
        status = status
    )
}