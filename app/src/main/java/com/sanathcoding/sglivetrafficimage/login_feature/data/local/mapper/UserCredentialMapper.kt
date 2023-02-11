package com.sanathcoding.sglivetrafficimage.login_feature.data.local.mapper

import com.sanathcoding.sglivetrafficimage.login_feature.data.local.UserCredentialEntity
import com.sanathcoding.sglivetrafficimage.login_feature.domain.model.UserCredential

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