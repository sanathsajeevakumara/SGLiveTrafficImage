package com.sanathcoding.sglivetrafficimage.map_feature.domain.model

import org.junit.Assert.*
import org.junit.Test

internal class CameraTest {

    @Test
    fun `doseMatchSearchQuery`() {

        val camera = Camera(
            cameraId = "Camera 1005",
            image = "",
            imageMetadata = ImageMetaData( height = 2, md5 = "", width = 2),
            location = Location( latitude = 2.0, longitude = 2.0),
            timestamp = ""
        )

        val doseMatchSearchText = camera.doseMatchSearchQuery("Camera 1005")
        assertEquals(doseMatchSearchText, true)

    }

}