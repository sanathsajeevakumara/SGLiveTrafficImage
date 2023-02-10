package com.sanathcoding.sglivetrafficimage.core.data.local.mapper

import com.sanathcoding.sglivetrafficimage.core.data.local.UserCredentialEntity
import com.sanathcoding.sglivetrafficimage.feature_login.domain.model.UserCredential

fun UserCredentialEntity.toUserCredential(): UserCredential {
    return UserCredential(
        userName = userName,
        password = password,
        id = id
    )
}

fun UserCredential.toUserCredentialEntity(): UserCredentialEntity {
    return UserCredentialEntity(
        userName = userName,
        password = password,
        id = id
    )
}