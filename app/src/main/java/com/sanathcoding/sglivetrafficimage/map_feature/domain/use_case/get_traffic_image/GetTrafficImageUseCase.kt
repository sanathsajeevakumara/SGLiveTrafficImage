package com.sanathcoding.sglivetrafficimage.map_feature.domain.use_case.get_traffic_image

import com.sanathcoding.sglivetrafficimage.core.common.Resource
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Camera
import com.sanathcoding.sglivetrafficimage.map_feature.domain.repository.TrafficImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrafficImageUseCase @Inject constructor(
    private val repository: TrafficImageRepository
) {
    operator fun invoke(): Flow<Resource<List<Camera>>> {
        return repository.getTrafficImages()
    }
}