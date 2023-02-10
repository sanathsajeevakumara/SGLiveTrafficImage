package com.sanathcoding.sglivetrafficimage.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.sanathcoding.sglivetrafficimage.data.local.UserCredentialDao
import com.sanathcoding.sglivetrafficimage.data.local.UserCredentialDataBase
import com.sanathcoding.sglivetrafficimage.data.local.repository.UserCredentialRepositoryImpl
import com.sanathcoding.sglivetrafficimage.domain.repository.UserCredentialRepository
import com.sanathcoding.sglivetrafficimage.domain.use_case.ValidatePassword
import com.sanathcoding.sglivetrafficimage.domain.use_case.ValidateUserName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserCredentialModule {

    @Singleton
    @Provides
    fun provideUserCredentialDatabase(app: Application): UserCredentialDataBase {
        return Room.databaseBuilder(
            app,
            UserCredentialDataBase::class.java,
            "user_credential.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserCredentialDao(db: UserCredentialDataBase): UserCredentialDao {
        return  db.dao
    }

    @Provides
    @Singleton
    fun provideValidateUsername(): ValidateUserName {
        return ValidateUserName()
    }

    @Provides
    @Singleton
    fun provideValidatePassword(): ValidatePassword {
        return ValidatePassword()
    }

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext app: Context,
        dao: UserCredentialDao
    ): UserCredentialRepository = UserCredentialRepositoryImpl(dao, app)

}