package com.sanathcoding.sglivetrafficimage.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserCredentialDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserCredentialEntity)

    @Query("SELECT COUNT() FROM usercredentialentity WHERE userName = :name")
    fun doseUserExist(name: String): Int

    @Query("SELECT * FROM usercredentialentity WHERE userName = :name")
    suspend fun getPasswordByUserName(name: String): UserCredentialEntity

    @Query("SELECT * FROM usercredentialentity")
    fun getUser(): Flow<List<UserCredentialEntity>>

}