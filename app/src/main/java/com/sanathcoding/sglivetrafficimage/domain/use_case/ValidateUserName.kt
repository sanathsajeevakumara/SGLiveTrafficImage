package com.sanathcoding.sglivetrafficimage.domain.use_case

import com.sanathcoding.sglivetrafficimage.R
import com.sanathcoding.sglivetrafficimage.core.constValue.ConstValue.MAX_NAME_LENGTH
import com.sanathcoding.sglivetrafficimage.core.constValue.ConstValue.MIN_NAME_LENGTH
import com.sanathcoding.sglivetrafficimage.domain.util.ValidationResult
import com.sanathcoding.sglivetrafficimage.core.util.UiText

class ValidateUserName {

    fun execute(userName: String): ValidationResult {
        if (userName.isBlank()) {
            return ValidationResult(
                isSuccessful = false,
                errorMsg = UiText.StringResource(
                    resId = R.string.user_name_blank_error,
                )
            )
        }
        if (userName.length < MIN_NAME_LENGTH) {
            return ValidationResult(
                isSuccessful = false,
                errorMsg = UiText.StringResource(
                    resId = R.string.user_name_min_error,
                )
            )
        }
        if (userName.length > MAX_NAME_LENGTH) {
            return ValidationResult(
                isSuccessful = false,
                errorMsg = UiText.StringResource(
                    resId = R.string.user_name_max_error,
                )
            )
        }
        return ValidationResult(
            isSuccessful = true
        )
    }

}