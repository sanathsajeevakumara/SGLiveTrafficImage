package com.sanathcoding.sglivetrafficimage.map_feature.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.sanathcoding.sglivetrafficimage.map_feature.data.util.JsonParser
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.ImageMetaData
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Location

@ProvidedTypeConverter
class ImageMetaDataConverter(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromImageMetaDataJson(json: String): ImageMetaData {
        return jsonParser.fromJson<ImageMetaData>(
            json,
            object : TypeToken<Location>(){}.type
        ) ?: ImageMetaData(height = 0, md5 = "", width = 0)
    }

    @TypeConverter
    fun toImageMetaDataJson(imageMetaData: ImageMetaData): String {
        return jsonParser.toJson(
            imageMetaData,
            object : TypeToken<Location>(){}.type
        ) ?: ""
    }
}