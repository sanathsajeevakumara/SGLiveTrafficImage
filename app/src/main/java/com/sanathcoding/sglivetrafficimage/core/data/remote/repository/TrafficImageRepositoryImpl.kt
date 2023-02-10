package com.sanathcoding.sglivetrafficimage.core.data.remote.repository

import com.sanathcoding.sglivetrafficimage.core.data.remote.TrafficImageApi
import com.sanathcoding.sglivetrafficimage.core.data.remote.dto.TrafficImageDto
import com.sanathcoding.sglivetrafficimage.map_feature.domain.repository.TrafficImageRepository
import javax.inject.Inject

class TrafficImageRepositoryImpl @Inject constructor(
    private val api: TrafficImageApi
): TrafficImageRepository {

    override suspend fun getCameras(): TrafficImageDto {
        return api.getTrafficCameras()
    }
}