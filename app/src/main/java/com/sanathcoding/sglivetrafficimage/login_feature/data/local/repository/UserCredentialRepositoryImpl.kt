package com.sanathcoding.sglivetrafficimage.login_feature.data.local.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sanathcoding.sglivetrafficimage.core.common.ConstValue.PREFERENCES_NAME
import com.sanathcoding.sglivetrafficimage.login_feature.data.local.UserCredentialDao
import com.sanathcoding.sglivetrafficimage.login_feature.data.local.UserCredentialEntity
import com.sanathcoding.sglivetrafficimage.login_feature.data.local.mapper.toUserCredential
import com.sanathcoding.sglivetrafficimage.login_feature.data.local.mapper.toUserCredentialEntity
import com.sanathcoding.sglivetrafficimage.login_feature.domain.model.UserCredential
import com.sanathcoding.sglivetrafficimage.login_feature.domain.repository.UserCredentialRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class UserCredentialRepositoryImpl @Inject constructor(
    private val dao: UserCredentialDao,
    private val context: Context
) : UserCredentialRepository {

    override suspend fun insertUser(user: UserCredential) {
        return dao.insertUser(user.toUserCredentialEntity())
    }

    override fun doseUserExist(name: String): Int {
        return dao.doseUserExist(name)
    }

    override suspend fun getPasswordByUserName(name: String): UserCredentialEntity {
        return dao.getPasswordByUserName(name)
    }

    override fun getUser(): Flow<List<UserCredential>> {
        return dao.getUser().map { user ->
            user.map {
                it.toUserCredential()
            }
        }
    }

    override suspend fun putString(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun getString(key: String): String? {
        return try {
            val preferencesKey = stringPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            preferences[preferencesKey]
        }catch (e: Exception){
            e.printStackTrace()
            null
        }
    }
}