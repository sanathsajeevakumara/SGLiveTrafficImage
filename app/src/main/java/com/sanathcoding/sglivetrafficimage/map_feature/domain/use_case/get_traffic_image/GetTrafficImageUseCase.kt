package com.sanathcoding.sglivetrafficimage.map_feature.domain.use_case.get_traffic_image

import com.sanathcoding.sglivetrafficimage.core.common.Resource
import com.sanathcoding.sglivetrafficimage.core.data.remote.mapper.toTrafficImage
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.TrafficImage
import com.sanathcoding.sglivetrafficimage.map_feature.domain.repository.TrafficImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTrafficImageUseCase @Inject constructor(
    private val repository: TrafficImageRepository
) {
    operator fun invoke(): Flow<Resource<TrafficImage>> = flow {
        try {
            emit(Resource.Loading())
            val images = repository.getTrafficImages()
            emit(Resource.Success(images.toTrafficImage()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server. Check your internet connection"))
        }
    }
}