package com.exam.moviedb.data.domain

interface ILocalDataSource {
    fun fetchMovies(): List<MovieDTO>
}