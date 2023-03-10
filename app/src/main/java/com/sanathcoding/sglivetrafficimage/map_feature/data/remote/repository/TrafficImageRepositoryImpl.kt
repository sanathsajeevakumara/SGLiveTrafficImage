package com.sanathcoding.sglivetrafficimage.map_feature.data.remote.repository

import android.content.Context
import com.sanathcoding.sglivetrafficimage.R
import com.sanathcoding.sglivetrafficimage.core.common.Resource
import com.sanathcoding.sglivetrafficimage.map_feature.data.local.CameraDao
import com.sanathcoding.sglivetrafficimage.map_feature.data.local.mapper.toCamera
import com.sanathcoding.sglivetrafficimage.map_feature.data.local.mapper.toCameraEntity
import com.sanathcoding.sglivetrafficimage.map_feature.data.remote.TrafficImageApi
import com.sanathcoding.sglivetrafficimage.map_feature.data.remote.mapper.toTrafficImage
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Camera
import com.sanathcoding.sglivetrafficimage.map_feature.domain.repository.TrafficImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TrafficImageRepositoryImpl @Inject constructor(
    private val api: TrafficImageApi,
    private val dao: CameraDao,
    private val context: Context
) : TrafficImageRepository {
    override fun getTrafficImages(): Flow<Resource<List<Camera>>> = flow {

        emit(Resource.Loading())
        val trafficImageCache = dao.getCameras().map { it.toCamera() }
        emit(Resource.Loading(trafficImageCache))

        try {
            val remoteTrafficImage = api.getTrafficImages().toTrafficImage()
            if (remoteTrafficImage.items.isNotEmpty()) {
                remoteTrafficImage.items.map { item ->
                    dao.deleteCameras()
                    dao.insertCamera(item.cameras.map {it.toCameraEntity()})
                }
            } else emit(Resource.Success(emptyList()))


        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: context.getString(R.string.http_exception)
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: context.getString(R.string.io_exception)
                )
            )
        }

        val newCameraDetails = dao.getCameras().map { it.toCamera() }
        emit(Resource.Success(newCameraDetails))

    }

    override fun getTrafficImageByDateTime(dateTime: String): Flow<Resource<List<Camera>>> = flow {
        emit(Resource.Loading())
        val trafficImageCache = dao.getCameras().map { it.toCamera() }
        emit(Resource.Loading(trafficImageCache))

        try {
            val remoteTrafficImage = api.getTrafficImageByDateTime(dateTime).toTrafficImage()
            if (remoteTrafficImage.items.isNotEmpty()) {
                remoteTrafficImage.items.map { item ->
                    dao.deleteCameras()
                    dao.insertCamera(item.cameras.map {it.toCameraEntity()})
                }
            } else emit(Resource.Success(emptyList()))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: context.getString(R.string.http_exception)
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: context.getString(R.string.io_exception)
                )
            )
        }

        val newCameraDetails = dao.getCameras().map { it.toCamera() }
        emit(Resource.Success(newCameraDetails))
    }


}