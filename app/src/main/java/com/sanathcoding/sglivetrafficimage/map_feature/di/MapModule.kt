package com.sanathcoding.sglivetrafficimage.map_feature.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.sanathcoding.sglivetrafficimage.core.common.ConstValue.BASE_URL
import com.sanathcoding.sglivetrafficimage.map_feature.data.ImageMetaDataConverter
import com.sanathcoding.sglivetrafficimage.map_feature.data.LocationConverter
import com.sanathcoding.sglivetrafficimage.map_feature.data.local.CameraDataBase
import com.sanathcoding.sglivetrafficimage.map_feature.data.remote.TrafficImageApi
import com.sanathcoding.sglivetrafficimage.map_feature.data.remote.repository.TrafficImageRepositoryImpl
import com.sanathcoding.sglivetrafficimage.map_feature.data.util.GsonParser
import com.sanathcoding.sglivetrafficimage.map_feature.data.util.JsonParser
import com.sanathcoding.sglivetrafficimage.map_feature.domain.repository.TrafficImageRepository
import com.sanathcoding.sglivetrafficimage.map_feature.domain.use_case.GetTrafficImageByDateTimeUseCase
import com.sanathcoding.sglivetrafficimage.map_feature.domain.use_case.GetTrafficImageUseCase
import com.sanathcoding.sglivetrafficimage.map_feature.domain.use_case.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapModule {

    @Provides
    @Singleton
    fun provideGetTrafficImageUseCase(repository: TrafficImageRepository): GetTrafficImageUseCase {
        return GetTrafficImageUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetTrafficImageByDateTimeUseCase(repository: TrafficImageRepository): GetTrafficImageByDateTimeUseCase {
        return GetTrafficImageByDateTimeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSearchUseCase(repository: TrafficImageRepository): SearchUseCase {
        return SearchUseCase()
    }

    @Provides
    @Singleton
    fun provideTrafficImageRepository(api: TrafficImageApi, db: CameraDataBase): TrafficImageRepository {
        return TrafficImageRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideCameraDatabase(app: Application): CameraDataBase {
        val locationConverter = LocationConverter(GsonParser(Gson()))
        val imageMetaDataConverter = ImageMetaDataConverter(GsonParser(Gson()))
        return Room.databaseBuilder(
            app,
            CameraDataBase::class.java,
            "camera.db"
        ).addTypeConverter(locationConverter)
            .addTypeConverter(imageMetaDataConverter)
            .build()
    }

    @Provides
    @Singleton
    fun provideTrafficImageApi(): TrafficImageApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TrafficImageApi::class.java)
    }

}