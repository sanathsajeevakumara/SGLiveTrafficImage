package com.sanathcoding.sglivetrafficimage.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserCredentialEntity(
    val userName: String,
    val password: String,
    @PrimaryKey val id:Int? = null
)
