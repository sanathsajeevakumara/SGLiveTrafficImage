package com.sanathcoding.sglivetrafficimage.domain.model

data class UserCredential(
    val userName: String,
    val password: String,
    val id: Int? = null
)
