package com.exam.moviedb.utils.gson

import android.util.Log
import com.exam.moviedb.data.domain.MovieDTO
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class MovieDeserializer: JsonDeserializer<List<MovieDTO>> {
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
        val list = jsonMovieResults?.map { it ->
                it.asJsonObject.let {
                    val movieObject = it
                    // gson.fromJson(movieObject, MovieDTO::class.java).copy(page = page)
                    MovieDTO(
                        movieObject.get("adult").asBoolean,
                        movieObject.get("backdrop_path").asString,
                        movieObject.get("genre_ids").asJsonArray.map { it.asInt },
                        movieObject.get("id").asInt,
                        movieObject.get("original_language").asString,
                        movieObject.get("original_title").asString,
                        movieObject.get("overview").asString,
                        movieObject.get("popularity").asInt,
                        movieObject.get("poster_path").asString,
                        movieObject.get("release_date").asString,
                        movieObject.get("title").asString,
                        movieObject.get("video").asBoolean,
                        movieObject.get("vote_average").asFloat,
                        movieObject.get("vote_count").asInt,
                        page
                    )
                }

        }
        return list.orEmpty()
    }

}