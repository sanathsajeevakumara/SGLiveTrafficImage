package com.sanathcoding.sglivetrafficimage.data.local.mapper

import com.sanathcoding.sglivetrafficimage.data.local.UserCredentialEntity
import com.sanathcoding.sglivetrafficimage.domain.model.UserCredential

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