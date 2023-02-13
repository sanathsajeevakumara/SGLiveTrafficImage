package com.sanathcoding.sglivetrafficimage.map_feature.domain.model

data class Camera(
    val cameraId: String,
    val image: String,
    val imageMetadata: ImageMetaData,
    val location: Location,
    val timestamp: String
) {
    fun doseMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "Camera$query",
            "Camera $query",
            "$query",
        )
        return matchingCombinations.any {
            it.contains(query, true)
        }
    }
}
