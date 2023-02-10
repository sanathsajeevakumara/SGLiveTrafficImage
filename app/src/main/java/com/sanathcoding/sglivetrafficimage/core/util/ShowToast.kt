package com.sanathcoding.sglivetrafficimage.core.util

import android.content.Context
import android.widget.Toast

fun Context.showToast(message: UiText) {
    Toast.makeText(this, message.asString(this), Toast.LENGTH_LONG).show()
}