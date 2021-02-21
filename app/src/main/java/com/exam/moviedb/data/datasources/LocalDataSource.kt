package com.exam.moviedb.data.datasources

import com.exam.moviedb.data.domain.MovieDTO
import com.exam.moviedb.data.domain.ILocalDataSource

class LocalDataSource: ILocalDataSource {
    override fun fetchMovies(): List<MovieDTO> {
        TODO("Not yet implemented")
    }
}