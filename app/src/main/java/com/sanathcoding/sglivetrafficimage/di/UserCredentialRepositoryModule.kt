package com.sanathcoding.sglivetrafficimage.di

import com.sanathcoding.sglivetrafficimage.data.local.repository.UserCredentialRepositoryImpl
import com.sanathcoding.sglivetrafficimage.domain.repository.UserCredentialRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserCredentialRepositoryModule {

//    @Binds
//    @Singleton
//    abstract fun bindUserCredentialRepository(
//        userCredentialRepositoryImpl: UserCredentialRepositoryImpl
//    ): UserCredentialRepository

}