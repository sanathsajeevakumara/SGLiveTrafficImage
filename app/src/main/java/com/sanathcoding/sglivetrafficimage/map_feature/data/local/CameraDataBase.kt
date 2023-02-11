package com.sanathcoding.sglivetrafficimage.map_feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sanathcoding.sglivetrafficimage.map_feature.data.ImageMetaDataConverter
import com.sanathcoding.sglivetrafficimage.map_feature.data.LocationConverter

@Database(
    entities = [CameraEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(LocationConverter::class, ImageMetaDataConverter::class)
abstract class CameraDataBase: RoomDatabase() {
    abstract val dao: CameraDao
}