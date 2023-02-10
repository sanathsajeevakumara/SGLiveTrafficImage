package com.sanathcoding.sglivetrafficimage.map_feature.domain.repository

import com.sanathcoding.sglivetrafficimage.core.data.remote.dto.TrafficImageDto

interface TrafficImageRepository {

    suspend fun getCameras(): TrafficImageDto

}