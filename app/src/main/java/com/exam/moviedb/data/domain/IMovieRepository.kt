package com.exam.moviedb.data.domain

interface IMovieRepository {
    fun fetchDataOnline(offset: Int, limit: Int): List<Movie>;
}