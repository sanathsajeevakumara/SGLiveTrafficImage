package com.sanathcoding.sglivetrafficimage.login_feature.presentation.loginScreen

sealed interface ValidateEvent {
    object Success: ValidateEvent
    object NewUserCreated: ValidateEvent
    object PasswordNotMatch: ValidateEvent
}