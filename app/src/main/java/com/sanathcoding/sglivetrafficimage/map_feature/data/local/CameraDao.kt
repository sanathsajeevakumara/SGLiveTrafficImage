package com.sanathcoding.sglivetrafficimage.map_feature.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CameraDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCamera(camera: List<CameraEntity>)

    @Query("DELETE * FROM cameraentity")
    suspend fun deleteCameras()

    @Query("SELECT * FROM cameraentity")
    suspend fun getCameras(): List<CameraEntity>
}