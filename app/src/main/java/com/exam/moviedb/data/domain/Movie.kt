package com.exam.moviedb.data.domain

data class Movie(
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val overview: String,
    val popularity: Int,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteCount: Int,
    val page: Int
)
