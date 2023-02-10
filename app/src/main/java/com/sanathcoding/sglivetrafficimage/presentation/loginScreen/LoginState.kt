package com.sanathcoding.sglivetrafficimage.presentation.loginScreen

import com.sanathcoding.sglivetrafficimage.domain.model.UserCredential

data class LoginState(
    val userName: String = "",
    val userNameErrorMsg: String? = null,
    val password: String = "",
    val passwordErrorMsg: String? = null,
    val userCredential: List<UserCredential> = emptyList()
)
