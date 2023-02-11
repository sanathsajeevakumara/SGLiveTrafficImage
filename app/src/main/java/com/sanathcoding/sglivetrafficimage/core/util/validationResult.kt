package com.sanathcoding.sglivetrafficimage.core.util


data class ValidationResult(
    val isSuccessful: Boolean,
    val errorMsg: UiText? = null
)