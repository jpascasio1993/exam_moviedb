package com.exam.moviedb.utils.gson

import android.util.JsonToken
import android.util.Log
import com.exam.moviedb.data.domain.MovieDTO
import com.google.gson.*
import java.lang.reflect.Type

class MovieDeserializer : JsonDeserializer<List<MovieDTO>> {
    private val gson = Gson()
    private val fields = listOf(
        "adult",
        "backdrop_path",
        "id",
        "genre_ids",
        "original_language",
        "original_title",
        "overview",
        "popularity",
        "poster_path",
        "release_date",
        "title",
        "video",
        "vote_average",
        "vote_count"
    )

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
                gson.fromJson(removeNull(it), MovieDTO::class.java)
                    .copy(genreIds = it.get("genre_ids").asJsonArray.map { it.asInt }, page = page)
            }
        }
        return list.orEmpty()
    }

    fun removeNull(json: JsonObject): JsonElement {
        return json.let { jsonObject ->
            val obj = JsonObject()
            fields.forEach {
                if (!jsonObject.has(it) || jsonObject.has(it) && jsonObject.get(it).isJsonNull) {
                    obj.addProperty(it, "")
                } else
                    obj.add(it, jsonObject.get(it))
            }.let {
                obj
            }
        }
    }
}