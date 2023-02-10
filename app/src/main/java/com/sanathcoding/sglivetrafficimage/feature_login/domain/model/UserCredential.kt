package com.sanathcoding.sglivetrafficimage.feature_login.domain.model

data class UserCredential(
    val userName: String,
    val password: String,
    val id: Int? = null
)
