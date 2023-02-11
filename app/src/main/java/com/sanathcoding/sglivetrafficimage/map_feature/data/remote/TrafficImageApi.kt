package com.sanathcoding.sglivetrafficimage.map_feature.data.remote

import com.sanathcoding.sglivetrafficimage.map_feature.data.remote.dto.TrafficImageDto
import retrofit2.http.GET

interface TrafficImageApi {

    @GET("/v1/transport/traffic-images")
    suspend fun getTrafficImages(): TrafficImageDto
}