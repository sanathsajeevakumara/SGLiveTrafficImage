package com.sanathcoding.sglivetrafficimage.map_feature.domain.repository

import com.sanathcoding.sglivetrafficimage.core.common.Resource
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Camera
import kotlinx.coroutines.flow.Flow

interface TrafficImageRepository {

    suspend fun getTrafficImages(): Flow<Resource<List<Camera>>>

}