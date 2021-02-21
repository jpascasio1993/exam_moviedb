package com.exam.moviedb.data.services

import com.exam.moviedb.data.domain.MovieDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ITheMovieDBService {
    @GET("/3/movie/popular?language=en-US")
    suspend fun getMovieList(@Query("api_key") key: String, @Query("page") page: Int): List<MovieDTO>
}