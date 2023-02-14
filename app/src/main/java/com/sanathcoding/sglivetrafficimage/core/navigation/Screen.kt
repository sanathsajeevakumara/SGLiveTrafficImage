package com.sanathcoding.sglivetrafficimage.core.navigation

import com.sanathcoding.sglivetrafficimage.core.common.ConstValue.CAMERA_DETAIL
import com.sanathcoding.sglivetrafficimage.core.common.ConstValue.HOME
import com.sanathcoding.sglivetrafficimage.core.common.ConstValue.LOGIN

sealed class Screen(val route: String) {
    object Login: Screen(LOGIN)
    object Home: Screen(HOME)
    object CameraDetail: Screen("$CAMERA_DETAIL/{cameraId}") {
        fun createRoute(cameraId: String) = "$CAMERA_DETAIL/$cameraId"
    }
}