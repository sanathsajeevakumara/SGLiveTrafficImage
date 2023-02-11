package com.sanathcoding.sglivetrafficimage.map_feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CameraEntity::class],
    version = 1
)
abstract class CameraDataBase: RoomDatabase() {
    abstract val dao: CameraDao
}