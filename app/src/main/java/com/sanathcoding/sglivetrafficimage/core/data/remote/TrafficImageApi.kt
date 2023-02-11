package com.sanathcoding.sglivetrafficimage.core.data.remote

import com.sanathcoding.sglivetrafficimage.core.data.remote.dto.CameraDto
import retrofit2.http.GET

interface TrafficImageApi {

    @GET("/v1/transport/traffic-images")
    suspend fun getTrafficImages(): List<CameraDto>
}