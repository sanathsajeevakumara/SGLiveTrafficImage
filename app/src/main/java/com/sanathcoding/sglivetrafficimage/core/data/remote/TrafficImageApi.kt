package com.sanathcoding.sglivetrafficimage.core.data.remote

import com.sanathcoding.sglivetrafficimage.core.data.remote.dto.TrafficImageDto
import retrofit2.http.GET

interface TrafficImageApi {

    @GET("/v1/transport/traffic-images")
    suspend fun getTrafficImages(): TrafficImageDto
}