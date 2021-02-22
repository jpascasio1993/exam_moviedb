package com.exam.moviedb.data.services

import retrofit2.Retrofit

object ServiceBuilder {
    fun<T> build(retrofit: Retrofit, service: Class<T>): T {
        return retrofit.create(service)
    }
}