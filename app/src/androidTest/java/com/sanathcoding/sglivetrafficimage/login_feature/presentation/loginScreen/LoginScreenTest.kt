package com.sanathcoding.sglivetrafficimage.login_feature.presentation.loginScreen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sanathcoding.sglivetrafficimage.MainActivity
import com.sanathcoding.sglivetrafficimage.core.common.TestTag.LOGIN_BUTTON
import com.sanathcoding.sglivetrafficimage.core.navigation.Screen
import com.sanathcoding.sglivetrafficimage.login_feature.di.LoginModule
import com.sanathcoding.sglivetrafficimage.ui.theme.SGLiveTrafficImageTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(LoginModule::class)
class LoginScreenTest {

    @get: Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get: Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeTestRule.setContent {
            val navController = rememberNavController()
            SGLiveTrafficImageTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Login.route
                ) {
                    composable(
                        route = Screen.Login.route
                    ) {
                        LoginScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun clickLoginButton_isClicked() {
        composeTestRule.onNodeWithTag(LOGIN_BUTTON).assertDoesNotExist()
        composeTestRule.onNodeWithTag(LOGIN_BUTTON).performClick()
        composeTestRule.onNodeWithTag(LOGIN_BUTTON).assertIsDisplayed()
    }

}