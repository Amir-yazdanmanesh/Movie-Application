package com.yazdanmanesh.movieapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.yazdanmanesh.movieapp.data.remote.models.Rating
import com.yazdanmanesh.movieapp.data.utils.JsonParser

@ProvidedTypeConverter
class DatabaseConverters(private val jsonParser: JsonParser) {

    @TypeConverter
    fun fromGenresJson(json: String): List<Rating> {
        return jsonParser.fromJson<ArrayList<Rating>>(
            json,
            object : TypeToken<ArrayList<Rating>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toGenresJson(movies: List<Rating>?): String {
        return jsonParser.toJson(
            movies,
            object : TypeToken<ArrayList<Rating>>() {}.type
        ) ?: "[]"
    }
}
