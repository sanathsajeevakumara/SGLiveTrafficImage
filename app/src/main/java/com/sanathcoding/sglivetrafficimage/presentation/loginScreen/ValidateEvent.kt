package com.sanathcoding.sglivetrafficimage.presentation.loginScreen

sealed interface ValidateEvent {
    object Success: ValidateEvent
    object DataAdded: ValidateEvent
    object PasswordNotMatch: ValidateEvent
}