package com.exam.moviedb.data.datasources

import com.exam.moviedb.data.domain.Movie
import com.exam.moviedb.data.domain.MovieDTO
import com.exam.moviedb.data.domain.Result

interface ILocalDataSource {
    fun getMovies(page: Int): Result<List<Movie>>
    suspend fun insertMovies(movies: List<MovieDTO>): Result<Unit>
    fun getMoviesCount(page: Int): Int
}