package com.exam.moviedb.utils.gson

import android.util.Log
import com.exam.moviedb.data.domain.MovieDTO
import com.google.gson.*
import java.lang.reflect.Type

class MovieDeserializer : JsonDeserializer<List<MovieDTO>> {
    private val gson: Gson = Gson()
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<MovieDTO> {
        val jsonObject = json?.asJsonObject
        Log.i("deserialize", "deserialize: ${jsonObject}")
        val jsonMovieResults = jsonObject?.getAsJsonArray("results")
        val page = jsonObject?.get("page")?.asInt ?: 0
        val list = jsonMovieResults?.map { movieResults ->
            movieResults.asJsonObject.let { it ->
                // gson.fromJson(movieObject, MovieDTO::class.java).copy(page = page)
                MovieDTO(
                    it.get("adult").asBoolean,
                    it.getNullable("backdrop_path")?.asString?: "",
                    it.get("genre_ids").asJsonArray.map { it.asInt },
                    it.get("id").asInt,
                    it.getNullable("original_language")?.asString?:"",
                    it.getNullable("original_title")?.asString?:"",
                    it.getNullable("overview")?.asString?:"",
                    it.get("popularity").asInt,
                    it.getNullable("poster_path")?.asString?:"",
                    it.getNullable("release_date")?.asString?:"",
                    it.getNullable("title")?.asString?:"",
                    it.get("video").asBoolean,
                    it.get("vote_average").asFloat,
                    it.get("vote_count").asInt,
                    page
                )

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