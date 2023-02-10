package com.sanathcoding.sglivetrafficimage.navigation.destination

import com.sanathcoding.sglivetrafficimage.core.constValue.ConstValue.CAMERA_DETAIL

sealed class CameraDetailScreenDestination(val route: String, val cameraId: String) {
    object CameraDetailScreen : CameraDetailScreenDestination(route = CAMERA_DETAIL, cameraId = "")
}
