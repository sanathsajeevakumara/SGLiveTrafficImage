package com.sanathcoding.sglivetrafficimage.login_feature.domain.model

data class UserCredential(
    val userName: String,
    val password: String,
    val id: Int? = null
)
