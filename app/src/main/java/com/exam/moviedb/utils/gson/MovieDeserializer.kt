package com.exam.moviedb.utils.gson

import android.util.Log
import com.exam.moviedb.data.domain.MovieDTO
import com.google.gson.*
import java.lang.reflect.Type

class MovieDeserializer : JsonDeserializer<List<MovieDTO>> {
    private val gson = Gson()
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<MovieDTO> {
        val jsonObject = json?.asJsonObject
        val jsonMovieResults = jsonObject?.getAsJsonArray("results")
        val page = jsonObject?.get("page")?.asInt ?: 0
            val list = jsonMovieResults?.map { movieResults ->
                movieResults.asJsonObject.let { it ->
                    gson.fromJson(it, MovieDTO::class.java).copy(genreIds = it.get("genre_ids").asJsonArray.map { it.asInt }, page = page)
                }
            }
            return list.orEmpty()
    }

}

fun JsonObject.getNullable(key: String): JsonElement? {
    val value: JsonElement = this.get(key) ?: return null

    if (value.isJsonNull) {
        return null
    }

    return value
}