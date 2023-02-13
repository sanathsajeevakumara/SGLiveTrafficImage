package com.sanathcoding.sglivetrafficimage.login_feature.presentation.loginScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sanathcoding.sglivetrafficimage.R
import com.sanathcoding.sglivetrafficimage.core.common.TestTag.LOGIN_BUTTON
import com.sanathcoding.sglivetrafficimage.core.navigation.Screen
import com.sanathcoding.sglivetrafficimage.core.util.UiText
import com.sanathcoding.sglivetrafficimage.core.util.showToast

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val revealPassword: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                ValidateEvent.Success -> {
                    context.showToast(UiText.StringResource(R.string.login_successful))
                    navController.navigate(Screen.Home.route)
                }
                ValidateEvent.DataAdded -> {
                    context.showToast(UiText.StringResource(R.string.account_created))
                    navController.navigate(Screen.Home.route)
                }
                ValidateEvent.PasswordNotMatch -> {
                    context.showToast(UiText.StringResource(R.string.password_not_match_error))
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "${UiText.StringResource(R.string.hello).asString()} ${viewModel.userName}!",
                modifier = Modifier.align(Alignment.CenterVertically),
                fontSize = 40.sp
            )
        }
        Spacer(modifier = Modifier.height(60.dp))

        TextField(
            value = viewModel.userName,
            onValueChange = { userName ->
                viewModel.updateUserName(userName)
                viewModel.onEvent(LoginEvent.UserNameChanged(userName))
            },
            isError = state.userNameErrorMsg != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = UiText.StringResource(R.string.username).asString()
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        if (state.userNameErrorMsg != null) {
            Text(
                text = state.userNameErrorMsg,
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = viewModel.password,
            onValueChange = { password ->
                viewModel.updatePassword(password)
                viewModel.onEvent(LoginEvent.PasswordChanged(password))

            },
            isError = state.passwordErrorMsg != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = UiText.StringResource(R.string.password).asString()
                )
            },
            visualTransformation = if (revealPassword.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                if (revealPassword.value) {
                    IconButton(
                        onClick = {
                            revealPassword.value = false
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "Visibility Icon"
                        )
                    }
                } else {
                    IconButton(
                        onClick = {
                            revealPassword.value = true
                        },
                    ) {

                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "Visibility Off icon"
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() })
        )
        if (state.passwordErrorMsg != null) {
            Text(
                text = state.passwordErrorMsg,
                color = MaterialTheme.colors.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                focusManager.clearFocus()
                viewModel.onEvent(LoginEvent.Login)
            },
            modifier = Modifier.align(Alignment.End).testTag(LOGIN_BUTTON),
        ) {
            Text(text = UiText.StringResource(R.string.login).asString())
        }

    }

}
