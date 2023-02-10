package com.sanathcoding.sglivetrafficimage.map_feature.domain.model

import com.sanathcoding.sglivetrafficimage.core.data.remote.dto.ApiInfo
import com.sanathcoding.sglivetrafficimage.core.data.remote.dto.Item

data class TrafficImage(
    val apiInfo: ApiInfo,
    val items: List<Item>
)
