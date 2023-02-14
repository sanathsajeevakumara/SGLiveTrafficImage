package com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen

sealed class MapEvent {
    object ToggleFallOutMap: MapEvent()
//    object OnFilterButtonClicked: MapEvent()
    object OnSort: MapEvent()
}
