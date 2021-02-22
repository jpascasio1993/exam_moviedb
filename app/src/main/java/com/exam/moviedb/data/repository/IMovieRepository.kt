package com.exam.moviedb.data.repository

import androidx.lifecycle.LiveData
import com.exam.moviedb.data.domain.Movie
import com.exam.moviedb.data.domain.Result

interface IMovieRepository {
    suspend fun getDataOnline(page: Int): Result<Unit>
    suspend fun getMovieList(page: Int): Result<List<Movie>>
    fun getMovieCount(page: Int): Int
}