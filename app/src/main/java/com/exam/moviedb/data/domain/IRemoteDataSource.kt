package com.exam.moviedb.data.domain

import retrofit2.http.GET

interface IRemoteDataSource {

    fun fetchMovies(): List<MovieDTO>
}