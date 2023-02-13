package com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen

import com.google.maps.android.compose.MapProperties
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Camera

data class MapState(
    val isLoading: Boolean = false,
    val properties: MapProperties = MapProperties(),
    val camera: List<Camera>? = emptyList(),
    val error: String = "",
    val isFavorite: Boolean = false
)
