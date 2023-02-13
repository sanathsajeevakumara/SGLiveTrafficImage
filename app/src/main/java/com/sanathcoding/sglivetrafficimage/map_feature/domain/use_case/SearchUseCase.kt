package com.sanathcoding.sglivetrafficimage.map_feature.domain.use_case

import android.util.Log
import com.sanathcoding.sglivetrafficimage.R
import com.sanathcoding.sglivetrafficimage.core.util.UiText
import com.sanathcoding.sglivetrafficimage.core.util.ValidationResult

class SearchUseCase {

    fun execute(query: String): Boolean {
        if (query.isBlank()) {
            return false
        }
        return true
    }
}