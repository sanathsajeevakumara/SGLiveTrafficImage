package com.sanathcoding.sglivetrafficimage.core.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface UiText {
    /*
    Only use when the string is coming from out side (ex: from API etc.)
    */
    data class DynamicString(val value: String): UiText

    /*
    Only use when the string is used inside the project (ex: Strings that we define)
    */
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ): UiText

    //use for composable function
    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> stringResource(resId, *args)
        }
    }

    //use for non composable function
    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, *args)
        }
    }
}