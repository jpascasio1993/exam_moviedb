package com.exam.moviedb.data.repository

import com.exam.moviedb.data.domain.Movie
import com.exam.moviedb.data.domain.IMovieRepository
import com.exam.moviedb.data.datasources.LocalDataSource
import com.exam.moviedb.data.datasources.RemoteDataSource

class MovieRepositoryImpl(private val localSource: LocalDataSource, private val remoteSource: RemoteDataSource):
    IMovieRepository {
    override fun fetchDataOnline(offset: Int, limit: Int): List<Movie> {
        TODO("Not yet implemented")
    }
}