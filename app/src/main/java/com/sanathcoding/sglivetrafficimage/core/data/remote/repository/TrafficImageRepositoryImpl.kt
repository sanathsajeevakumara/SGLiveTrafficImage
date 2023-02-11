package com.sanathcoding.sglivetrafficimage.core.data.remote.repository

import com.sanathcoding.sglivetrafficimage.core.common.Resource
import com.sanathcoding.sglivetrafficimage.core.data.remote.TrafficImageApi
import com.sanathcoding.sglivetrafficimage.core.data.remote.mapper.toCamera
import com.sanathcoding.sglivetrafficimage.core.data.remote.mapper.toTrafficImage
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Camera
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.TrafficImage
import com.sanathcoding.sglivetrafficimage.map_feature.domain.repository.TrafficImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TrafficImageRepositoryImpl @Inject constructor(
    private val api: TrafficImageApi
) : TrafficImageRepository {
    override fun getTrafficImages(): Flow<Resource<List<Camera>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Loading())
            val trafficImage = api.getTrafficImages().toTrafficImage()
            if (trafficImage.items.isNotEmpty()) {
                trafficImage.items.map { item ->
                    emit(Resource.Success(item.cameras))
                }
            } else emit(Resource.Success(emptyList()))


        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection"
                )
            )
        }
    }


}