package com.exam.moviedb.data.domain

data class Movie(
    val id: Int,
    val page: Int,
    val results: List<ResultDTO>,
    val totalPages: Int,
    val totalResults: Int
)

data class Result(
    val id: Int,
    val itemCount: Int,
    val description: String,
    val favoriteCount: String,
    val language: String,
    val listType: String,
    val name: String,
    val posterPath: String
)
