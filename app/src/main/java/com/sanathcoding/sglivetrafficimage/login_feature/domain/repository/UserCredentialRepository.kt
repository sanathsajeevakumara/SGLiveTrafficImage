package com.sanathcoding.sglivetrafficimage.login_feature.domain.repository

import com.sanathcoding.sglivetrafficimage.login_feature.data.local.UserCredentialEntity
import com.sanathcoding.sglivetrafficimage.login_feature.domain.model.UserCredential
import kotlinx.coroutines.flow.Flow

interface UserCredentialRepository {

    suspend fun insertUser(user: UserCredential)

//    suspend fun getIsUserNameAvailable(name: String): String

//    fun getUserByUserName(name: String): UserCredentialEntity

    fun doseUserExist(name: String): Int


    suspend fun getPasswordByUserName(name: String): UserCredentialEntity

//    suspend fun getPasswordByUserName(name: String): String

    fun getUser(): Flow<List<UserCredential>>

    suspend fun putString(key: String, value: String)

    suspend fun getString(key: String): String?

}