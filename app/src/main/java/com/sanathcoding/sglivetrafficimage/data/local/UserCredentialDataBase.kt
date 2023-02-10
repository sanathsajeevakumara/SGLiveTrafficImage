package com.sanathcoding.sglivetrafficimage.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserCredentialEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UserCredentialDataBase: RoomDatabase() {
    abstract val dao: UserCredentialDao
}