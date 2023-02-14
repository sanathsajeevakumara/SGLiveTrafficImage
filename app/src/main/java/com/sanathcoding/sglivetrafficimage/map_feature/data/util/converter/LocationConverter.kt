package com.sanathcoding.sglivetrafficimage.map_feature.data.util.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.sanathcoding.sglivetrafficimage.map_feature.data.util.JsonParser
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.ImageMetaData
import com.sanathcoding.sglivetrafficimage.map_feature.domain.model.Location

@ProvidedTypeConverter
class LocationConverter(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromLocationJson(json: String): Location {
        return jsonParser.fromJson<Location>(
            json,
            object : TypeToken<Location>(){}.type
        ) ?: Location(latitude = 0.0, longitude = 0.0)
    }

    @TypeConverter
    fun toLocationJson(location: Location): String {
        return jsonParser.toJson(
            location,
            object : TypeToken<Location>(){}.type
        ) ?: ""
    }
}