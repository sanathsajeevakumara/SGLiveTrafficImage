package com.sanathcoding.sglivetrafficimage.map_feature.di

import android.app.Application
import androidx.room.Room
import com.sanathcoding.sglivetrafficimage.core.common.ConstValue.BASE_URL
import com.sanathcoding.sglivetrafficimage.map_feature.data.local.CameraDataBase
import com.sanathcoding.sglivetrafficimage.map_feature.data.remote.TrafficImageApi
import com.sanathcoding.sglivetrafficimage.map_feature.data.remote.repository.TrafficImageRepositoryImpl
import com.sanathcoding.sglivetrafficimage.map_feature.domain.repository.TrafficImageRepository
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
    fun provideTrafficImageRepository(api: TrafficImageApi, db: CameraDataBase): TrafficImageRepository {
        return TrafficImageRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideCameraDatabase(app: Application): CameraDataBase {
        return Room.databaseBuilder(
            app,
            CameraDataBase::class.java,
            "camera.db"
        ).build()
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

    @Provides
    @Singleton
    fun provideSearchUseCase(): SearchUseCase {
        return SearchUseCase()
    }

}