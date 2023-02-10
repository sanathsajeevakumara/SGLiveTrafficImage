package com.sanathcoding.sglivetrafficimage.login_feature.presentation.loginScreen

sealed interface LoginEvent {
    data class UserNameChanged(val userName: String) : LoginEvent
    data class PasswordChanged(val password: String) : LoginEvent
    object Login : LoginEvent
}