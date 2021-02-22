package com.exam.moviedb.data.datasources

import com.exam.moviedb.data.domain.MovieDTO

interface IRemoteDataSource {

    suspend fun fetchMovies(page: Int): List<MovieDTO>
}