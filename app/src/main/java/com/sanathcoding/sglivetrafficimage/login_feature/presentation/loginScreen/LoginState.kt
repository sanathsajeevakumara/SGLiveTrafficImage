package com.sanathcoding.sglivetrafficimage.login_feature.presentation.loginScreen

import com.sanathcoding.sglivetrafficimage.login_feature.domain.model.UserCredential

data class LoginState(
    val userName: String = "",
    val userNameErrorMsg: String? = null,
    val password: String = "",
    val passwordErrorMsg: String? = null,
    val userCredential: List<UserCredential> = emptyList()
)
