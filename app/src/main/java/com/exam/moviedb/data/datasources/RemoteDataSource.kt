package com.exam.moviedb.data.datasources

import com.exam.moviedb.data.domain.ApiKey
import com.exam.moviedb.data.domain.MovieDTO
import com.exam.moviedb.data.services.ITheMovieDBService

class RemoteDataSource(private val apiKey: ApiKey, private val service: ITheMovieDBService):
    IRemoteDataSource {
    override suspend fun fetchMovies(page: Int): List<MovieDTO> {
        return service.getMovieList(apiKey.key, page)
    }
}