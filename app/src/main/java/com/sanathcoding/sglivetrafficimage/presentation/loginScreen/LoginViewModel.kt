package com.sanathcoding.sglivetrafficimage.presentation.loginScreen

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanathcoding.sglivetrafficimage.core.constValue.ConstValue.USER_NAME
import com.sanathcoding.sglivetrafficimage.domain.model.UserCredential
import com.sanathcoding.sglivetrafficimage.domain.repository.UserCredentialRepository
import com.sanathcoding.sglivetrafficimage.domain.use_case.ValidatePassword
import com.sanathcoding.sglivetrafficimage.domain.use_case.ValidateUserName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserCredentialRepository,
    private val validateUserName: ValidateUserName,
    private val validatePassword: ValidatePassword,
    private val application: Application
) : ViewModel() {

    var state by mutableStateOf(LoginState())

    init {
        viewModelScope.launch {
            repository.getUser().collectLatest { user ->
                state = state.copy(
                    userCredential = user
                )
            }
        }
    }

    var userName by mutableStateOf(
        getUserNameFromDataStore() ?: ""
    )
        private set
    var password by mutableStateOf("")
        private set

    private val validationEventChannel = Channel<ValidateEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    private var passwordFromDb: String? = ""
    private var userCount = 0

    fun updateUserName(email: String) {
        userName = email
    }

    fun updatePassword(userPassword: String) {
        password = userPassword
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.UserNameChanged -> {
                state = state.copy(userName = event.userName)
            }
            is LoginEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            LoginEvent.Login -> loginUser()
        }
    }

    private fun loginUser() {

        val userNameResult = validateUserName.execute(userName)
        val passwordResult = validatePassword.execute(password)

        val hasError = listOf(
            userNameResult,
            passwordResult
        ).any { !it.isSuccessful }

        state = state.copy(
            userNameErrorMsg = userNameResult.errorMsg?.asString(application.applicationContext),
            passwordErrorMsg = passwordResult.errorMsg?.asString(application.applicationContext)
        )
        if (hasError) return

        viewModelScope.launch(Dispatchers.IO) {
            userCount = repository.doseUserExist(userName)
            if (userName == getUserNameFromDataStore()) {
                passwordFromDb = repository.getPasswordByUserName(userName).password
                if (password == passwordFromDb) {
                    withContext(Dispatchers.Main) {
                        validationEventChannel.send(ValidateEvent.Success)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        validationEventChannel.send(ValidateEvent.PasswordNotMatch)
                    }
                }
            } else {
                if (userCount == 0) {
                    insertUserToDataBase(userName, password)
                    saveUserNameToDataStore(userName)
                    withContext(Dispatchers.Main) {
                        validationEventChannel.send(ValidateEvent.DataAdded)
                    }
                } else {
                    passwordFromDb = repository.getPasswordByUserName(userName).password
                    if (password == passwordFromDb) {
                        withContext(Dispatchers.Main) {
                            validationEventChannel.send(ValidateEvent.Success)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            validationEventChannel.send(ValidateEvent.PasswordNotMatch)
                        }
                    }

                }
            }

        }
    }

    private fun insertUserToDataBase(userName: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(
                UserCredential(
                    userName = userName,
                    password = password
                )
            )
        }
    }

    private fun saveUserNameToDataStore(name: String) {
        viewModelScope.launch {
            repository.putString(USER_NAME, name)
        }
    }

    private fun getUserNameFromDataStore(): String? = runBlocking {
        repository.getString(USER_NAME)
    }

}