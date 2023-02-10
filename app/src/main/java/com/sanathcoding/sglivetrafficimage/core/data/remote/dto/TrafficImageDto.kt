package com.sanathcoding.sglivetrafficimage.core.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TrafficImageDto(
    @SerializedName("api_info")
    val apiInfo: ApiInfo,
    val items: List<Item>
)