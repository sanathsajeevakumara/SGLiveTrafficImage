package com.sanathcoding.sglivetrafficimage.map_feature.domain.use_case

import com.sanathcoding.sglivetrafficimage.core.common.Resource
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Camera
import com.sanathcoding.sglivetrafficimage.map_feature.domain.repository.TrafficImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrafficImageByDateTimeUseCase @Inject constructor(
    private val repository: TrafficImageRepository
) {
    operator fun invoke(dateTime: String): Flow<Resource<List<Camera>>> {
        return repository.getTrafficImageByDateTime(dateTime)
    }
}