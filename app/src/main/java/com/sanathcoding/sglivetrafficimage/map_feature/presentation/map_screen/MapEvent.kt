package com.sanathcoding.sglivetrafficimage.map_feature.presentation.map_screen

sealed class MapEvent {
    data class ShowSnackBar(val message: String): MapEvent()
}
