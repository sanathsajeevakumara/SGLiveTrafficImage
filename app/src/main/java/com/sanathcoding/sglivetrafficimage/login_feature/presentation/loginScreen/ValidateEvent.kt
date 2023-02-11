package com.sanathcoding.sglivetrafficimage.login_feature.presentation.loginScreen

sealed interface ValidateEvent {
    object Success: ValidateEvent
    object DataAdded: ValidateEvent
    object PasswordNotMatch: ValidateEvent
}