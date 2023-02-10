package com.sanathcoding.sglivetrafficimage.core.common

import com.sanathcoding.sglivetrafficimage.core.util.UiText

sealed class Resource<T>(val data: T? = null, val message: UiText? = null) {
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, UiText.DynamicString(message))
    class Loading<T>(data: T? = null): Resource<T>(data)
}
