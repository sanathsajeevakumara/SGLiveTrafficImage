package com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen

import com.google.maps.android.compose.MapProperties
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.TrafficImage

data class MapState(
    val isLoading: Boolean = false,
    val properties: MapProperties = MapProperties(),
    val trafficImage: TrafficImage? = null,
//    val isFallOutMap: Boolean = false,
    val error: String = ""
)
