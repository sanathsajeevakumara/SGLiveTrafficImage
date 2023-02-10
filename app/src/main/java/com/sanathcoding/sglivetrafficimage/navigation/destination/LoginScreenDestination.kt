package com.sanathcoding.sglivetrafficimage.navigation.destination

import com.sanathcoding.sglivetrafficimage.core.constValue.ConstValue.LOGIN

sealed class LoginScreenDestination(val route: String) {
    object Login : LoginScreenDestination(route = LOGIN)
}