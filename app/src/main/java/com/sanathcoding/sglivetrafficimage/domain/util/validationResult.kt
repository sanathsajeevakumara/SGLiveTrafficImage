package com.sanathcoding.sglivetrafficimage.domain.util

import com.sanathcoding.sglivetrafficimage.core.util.UiText

data class ValidationResult(
    val isSuccessful: Boolean,
    val errorMsg: UiText? = null
)