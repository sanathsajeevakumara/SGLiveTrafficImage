package com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanathcoding.sglivetrafficimage.core.common.Resource
import com.sanathcoding.sglivetrafficimage.map_feature.domain.use_case.GetTrafficImageUseCase
import com.sanathcoding.sglivetrafficimage.map_feature.domain.use_case.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getTrafficImageUseCase: GetTrafficImageUseCase,
    private val searchUseCase: SearchUseCase
): ViewModel() {

    var mapState by mutableStateOf(MapState())

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _mapEvent = MutableSharedFlow<MapEvent>()
    val mapEvent = _mapEvent.asSharedFlow()


    init {
        getTrafficImages()
    }

//    private val _camera = MutableStateFlow(cameraList)
//    val cameras = searchText.combine(_camera) { text, camera ->
//        val queryValidation = searchUseCase.execute(text)
//        if (queryValidation.isSuccessful) {
//            camera?.filter {
//                searchUseCase.doseMatchSearchQuery(text)
//            }
//        } else camera
//    }.stateIn(
//        viewModelScope,
//        SharingStarted.WhileSubscribed(5000L),
//        _camera.value
//    )


    private fun getTrafficImages() {
        getTrafficImageUseCase().onEach { resource ->
            when (resource) {
                is Resource.Error -> {
                    mapState = mapState.copy(
                        error = resource.message ?: "An Unexpected error occurred",
                        isLoading = false
                    )
                    _mapEvent.emit(MapEvent.ShowSnackBar(
                        resource.message?: "Unknown error"
                    ))
                }
                is Resource.Loading -> {
                    mapState = mapState.copy(
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    mapState = mapState.copy(
                        camera = resource.data ?: emptyList(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

}