package com.sanathcoding.sglivetrafficimage.presentation.loginScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sanathcoding.sglivetrafficimage.R
import com.sanathcoding.sglivetrafficimage.core.util.UiText
import com.sanathcoding.sglivetrafficimage.core.util.showToast
import com.sanathcoding.sglivetrafficimage.navigation.Graph

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                ValidateEvent.Success -> {
                    context.showToast(UiText.StringResource(R.string.login_successful))
                    navController.navigate(Graph.HOME_SCREEN)
                }
                ValidateEvent.DataAdded -> {
                    context.showToast(UiText.StringResource(R.string.account_created))
                    navController.navigate(Graph.HOME_SCREEN)
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
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
                viewModel.onEvent(LoginEvent.Login)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = UiText.StringResource(R.string.login).asString())
        }

    }

}