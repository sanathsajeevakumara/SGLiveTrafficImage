package com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.MapStyleOptions
import com.sanathcoding.sglivetrafficimage.core.common.Resource
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Camera
import com.sanathcoding.sglivetrafficimage.map_feature.domain.use_case.GetTrafficImageByDateTimeUseCase
import com.sanathcoding.sglivetrafficimage.map_feature.domain.use_case.GetTrafficImageUseCase
import com.sanathcoding.sglivetrafficimage.map_feature.domain.use_case.SearchUseCase
import com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen.component.DarkMapStyle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getTrafficImageUseCase: GetTrafficImageUseCase,
    private val getTrafficImageByDateTimeUseCase: GetTrafficImageByDateTimeUseCase,
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    var mapState by mutableStateOf(MapState())

    var cameras = mutableListOf<Camera>()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    init {
        getTrafficImages()
    }

    fun onEvent(event: MapEvent) {
        when (event) {
            MapEvent.toggleFallOutMap -> {
                mapState = mapState.copy(
                    properties = mapState.properties.copy(
                        mapStyleOptions = if (mapState.isFallOutMap) {
                            MapStyleOptions(DarkMapStyle.json)
                        } else null
                    ),
                    isFallOutMap = !mapState.isFallOutMap
                )
            }
        }
    }

    private val _cameraList = MutableStateFlow(cameras)
    val cameraList = searchQuery
        .onEach { _isSearching.update { true } }
        .combine(_cameraList) { searchQuery, cameraList ->
            if (searchUseCase.execute(searchQuery)) {
                cameraList
            } else {
                delay(2000L)
                cameraList.filter {
                it.doseMatchSearchQuery(searchQuery)
//                    it.cameraId.contains(searchQuery)
                }
            }
        }
        .onEach { _isSearching.update { true } }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _cameraList.value
        )

    private fun getTrafficImages() {
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

    fun getTrafficImageByDateTime(date: String, time: String) {

        val apiDateFormat = date + "T" + time + "Z"
        Log.d("Map", "API date time : $apiDateFormat")
        val encodedDateTime = URLEncoder.encode(apiDateFormat, "utf-8")
        Log.d("Map", "Encoded date time : $encodedDateTime")

        getTrafficImageByDateTimeUseCase(encodedDateTime).onEach { resource ->
            when (resource) {
                is Resource.Error -> {
                    Log.d("Filter data", "On error section")
                    mapState = mapState.copy(
                        error = resource.message ?: "An Unexpected error occurred",
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    Log.d("Filter data", "On Loading section")
                    mapState = mapState.copy(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    Log.d("Filter data", "On Success section")
                    mapState = mapState.copy(
                        camera = resource.data ?: emptyList(),
                        isLoading = false,
                    )
                    resource.data?.map {
                        Log.d("Filter data", "Filter Data : $it")
                    }
                }
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchQuery.value = text
    }

}