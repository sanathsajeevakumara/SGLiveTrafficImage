package com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.MapStyleOptions
import com.sanathcoding.sglivetrafficimage.core.common.Resource
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Camera
import com.sanathcoding.sglivetrafficimage.map_feature.domain.use_case.GetTrafficImageByDateTimeUseCase
import com.sanathcoding.sglivetrafficimage.map_feature.domain.use_case.GetTrafficImageUseCase
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen.component.DarkMapStyle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.net.URLEncoder
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class MapViewModel @Inject constructor(
    private val getTrafficImageUseCase: GetTrafficImageUseCase,
    private val getTrafficImageByDateTimeUseCase: GetTrafficImageByDateTimeUseCase,
) : ViewModel() {

    var mapState by mutableStateOf(MapState())

    private val cameras = mutableListOf<Camera>()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    init {
        getTrafficImages()
    }

    private val _cameraList = MutableStateFlow(cameras)
    var ascending = MutableStateFlow(true)


    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.ToggleFallOutMap -> {
                mapState = mapState.copy(
                    properties = mapState.properties.copy(
                        mapStyleOptions = if (mapState.isFallOutMap) {
                            MapStyleOptions(DarkMapStyle.json)
                        } else null
                    ),
                    isFallOutMap = !mapState.isFallOutMap
                )
            }
            is MapEvent.OnSort -> {
                mapState = mapState.copy(
                    camera = if (ascending.value) {
                        cameras.reversed()
                    } else {
                        cameras
                    }
                )
            }
        }
    }

    var cameraList = searchQuery
        .debounce(500L)
        .onEach { _isSearching.update { true } }
        .combine(_cameraList) { searchQuery, cameraList ->
            if (searchQuery.isBlank()) {
                cameraList
            } else {
                delay(2000L)
                cameraList.filter {
                    it.cameraId == searchQuery
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _cameraList.value
        )

    private fun getTrafficImages() {
        viewModelScope.launch(Dispatchers.IO) {
            getTrafficImageUseCase().onEach { resource ->
                when (resource) {
                    is Resource.Error -> {
                        mapState = mapState.copy(
                            error = resource.message ?: "An Unexpected error occurred",
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        mapState = mapState.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        mapState = mapState.copy(
                            camera = resource.data ?: emptyList(),
                            isLoading = false,
                        )
                        resource.data?.forEach {
                            cameras.add(it)
                        }


                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getTrafficImageByDateTime(date: String, time: String) {

        val apiDateFormat = date + "T" + time + "Z"
        val encodedDateTime = URLEncoder.encode(apiDateFormat, "utf-8")

        viewModelScope.launch(Dispatchers.IO) {
            getTrafficImageByDateTimeUseCase(encodedDateTime).onEach { resource ->
                when (resource) {
                    is Resource.Error -> {
                        mapState = mapState.copy(
                            error = resource.message ?: "An Unexpected error occurred",
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        mapState = mapState.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        mapState = mapState.copy(
                            camera = resource.data ?: emptyList(),
                            isLoading = false,
                        )
                    }
                }
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchQuery.value = text
    }

}