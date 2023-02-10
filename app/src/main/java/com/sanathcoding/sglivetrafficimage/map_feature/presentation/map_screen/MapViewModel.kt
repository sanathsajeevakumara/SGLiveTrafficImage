package com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanathcoding.sglivetrafficimage.core.common.Resource
import com.sanathcoding.sglivetrafficimage.core.util.UiText
import com.sanathcoding.sglivetrafficimage.map_feature.domain.use_case.get_traffic_image.GetTrafficImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getTrafficImageUseCase: GetTrafficImageUseCase
): ViewModel() {

    var mapState by mutableStateOf(MapState())

    init {
        getTrafficImages()
    }

    private fun getTrafficImages() {
        getTrafficImageUseCase().onEach { resource ->
            when (resource) {
                is Resource.Error -> {
                    mapState = mapState.copy(
                        error = resource.message ?: "An Unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    mapState = mapState.copy(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    mapState = mapState.copy(
                        trafficImage = resource.data
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}