package com.sanathcoding.sglivetrafficimage.feature_login.domain.use_case

import com.sanathcoding.sglivetrafficimage.R
import com.sanathcoding.sglivetrafficimage.feature_login.domain.util.ValidationResult
import com.sanathcoding.sglivetrafficimage.core.util.UiText

class ValidatePassword {

    fun execute(password: String): ValidationResult {

        val containsLetterDigitsAndSpecialChars = password.any { it.isLetter() }
                && password.any {it.isDigit()}
                && password.any { it.isUpperCase() }
                && password.any { it.isLowerCase() }
                && password.contains("[!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())

        if (password.isBlank()) {
            return ValidationResult(
                isSuccessful = false,
                errorMsg = UiText.StringResource(
                    resId = R.string.password_blank_error,
                )
            )
        }
        if (!containsLetterDigitsAndSpecialChars) {
            return ValidationResult(
                isSuccessful = false,
                errorMsg = UiText.StringResource(
                    resId = R.string.password_dont_contain_letter_or_digit_error,
                )
            )
        }
        return ValidationResult(
            isSuccessful = true
        )
    }

}