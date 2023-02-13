package com.sanathcoding.sglivetrafficimage.map_feature.domain.repository

import com.sanathcoding.sglivetrafficimage.core.common.Resource
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Camera
import kotlinx.coroutines.flow.Flow

interface TrafficImageRepository {

    fun getTrafficImages(): Flow<Resource<List<Camera>>>

    fun getTrafficImageByDateTime(dateTime: String): Flow<Resource<List<Camera>>>

}