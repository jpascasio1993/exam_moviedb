package com.exam.moviedb.data.domain

import com.google.gson.annotations.SerializedName

data class MovieDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<ResultDTO>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

data class ResultDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("item_count") val itemCount: Int,
    @SerializedName("description") val description: String,
    @SerializedName("favorite_count") val favoriteCount: String,
    @SerializedName("iso_639_1") val language: String,
    @SerializedName("list_type") val listType: String,
    @SerializedName("name") val name: String,
    @SerializedName("poster_path") val posterPath: String
)
