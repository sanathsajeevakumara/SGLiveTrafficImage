package com.sanathcoding.sglivetrafficimage.login_feature.domain.repository

import com.sanathcoding.sglivetrafficimage.login_feature.data.local.UserCredentialEntity
import com.sanathcoding.sglivetrafficimage.login_feature.domain.model.UserCredential
import kotlinx.coroutines.flow.Flow

interface UserCredentialRepository {

    suspend fun insertUser(user: UserCredential)

    fun doseUserExist(name: String): Int

    suspend fun getPasswordByUserName(name: String): UserCredentialEntity

    fun getUser(): Flow<List<UserCredential>>

    suspend fun putString(key: String, value: String)

    suspend fun getString(key: String): String?

}