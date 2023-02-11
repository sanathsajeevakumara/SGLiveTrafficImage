package com.sanathcoding.sglivetrafficimage.map_feature.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TrafficImageDto(
    @SerializedName("api_info")
    val apiInfo: ApiInfoDto,
    val items: List<ItemDto>
)