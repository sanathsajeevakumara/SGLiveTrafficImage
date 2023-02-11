package com.sanathcoding.sglivetrafficimage.map_feature.di

import com.sanathcoding.sglivetrafficimage.core.common.ConstValue.BASE_URL
import com.sanathcoding.sglivetrafficimage.core.data.remote.TrafficImageApi
import com.sanathcoding.sglivetrafficimage.core.data.remote.repository.TrafficImageRepositoryImpl
import com.sanathcoding.sglivetrafficimage.map_feature.domain.repository.TrafficImageRepository
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
    fun provideTrafficImageApi(): TrafficImageApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TrafficImageApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTrafficImageRepository(api: TrafficImageApi): TrafficImageRepository {
        return TrafficImageRepositoryImpl(api)
    }

}