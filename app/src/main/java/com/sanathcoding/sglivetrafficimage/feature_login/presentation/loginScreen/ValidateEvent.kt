package com.sanathcoding.sglivetrafficimage.feature_login.presentation.loginScreen

sealed interface ValidateEvent {
    object Success: ValidateEvent
    object DataAdded: ValidateEvent
    object PasswordNotMatch: ValidateEvent
}