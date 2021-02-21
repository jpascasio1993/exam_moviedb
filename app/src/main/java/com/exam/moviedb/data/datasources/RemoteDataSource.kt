package com.exam.moviedb.data.datasources

import com.exam.moviedb.data.domain.MovieDTO
import com.exam.moviedb.data.domain.IRemoteDataSource
import com.exam.moviedb.data.services.ITheMovieDBService
import retrofit2.Retrofit

class RemoteDataSource(private val retrofit: Retrofit, private val service: ITheMovieDBService): IRemoteDataSource {
    override fun fetchMovies(): List<MovieDTO> {
        TODO("Not yet implemented")
    }
}