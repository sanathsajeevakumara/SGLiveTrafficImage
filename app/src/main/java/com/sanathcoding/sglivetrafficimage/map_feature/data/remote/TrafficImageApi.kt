package com.sanathcoding.sglivetrafficimage.map_feature.data.remote

import com.sanathcoding.sglivetrafficimage.map_feature.data.remote.dto.TrafficImageDto
import retrofit2.http.GET
import retrofit2.http.Path

interface TrafficImageApi {

    @GET("/v1/transport/traffic-images")
    suspend fun getTrafficImages(): TrafficImageDto

    @GET("/v1/transport/traffic-images/{date_time}")
    suspend fun getTrafficImageByDateTime(
        @Path("date_time") dateTime: String
    ):TrafficImageDto
}