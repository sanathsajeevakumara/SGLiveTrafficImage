package com.sanathcoding.sglivetrafficimage.login_feature.domain.util

import com.sanathcoding.sglivetrafficimage.core.util.UiText

data class ValidationResult(
    val isSuccessful: Boolean,
    val errorMsg: UiText? = null
)