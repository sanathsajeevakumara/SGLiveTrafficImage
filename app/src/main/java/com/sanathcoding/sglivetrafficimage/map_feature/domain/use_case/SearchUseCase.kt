package com.sanathcoding.sglivetrafficimage.map_feature.domain.use_case

import com.sanathcoding.sglivetrafficimage.R
import com.sanathcoding.sglivetrafficimage.core.util.UiText
import com.sanathcoding.sglivetrafficimage.core.util.ValidationResult

class SearchUseCase {

    fun execute(query: String): ValidationResult {
        if (query.isBlank()) {
            return ValidationResult(
                isSuccessful = false,
                errorMsg = UiText.StringResource(
                    resId = R.string.query_blank_error
                )
            )
        }
        return ValidationResult(
            isSuccessful = true
        )
    }

    fun doseMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "Camera$query",
            "Camera $query",
            "Camera ${query.first()}",
        )
        return matchingCombinations.any {
            it.contains(query)
        }
    }
}