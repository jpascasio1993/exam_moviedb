package com.exam.moviedb.di

import com.exam.moviedb.data.datasources.LocalDataSource
import com.exam.moviedb.data.datasources.RemoteDataSource
import com.exam.moviedb.data.domain.ILocalDataSource
import com.exam.moviedb.data.domain.IRemoteDataSource
import com.exam.moviedb.data.services.ITheMovieDBService
import com.exam.moviedb.data.services.ServiceBuilder
import com.exam.moviedb.db.MovieDB
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DIModule {
    val modules = module {

        single {
            MovieDB(get())
        }

        single<ILocalDataSource> {
            LocalDataSource()
        }

        single<IRemoteDataSource> {
            RemoteDataSource(get(), get())
        }

        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        single<ITheMovieDBService> {
            ServiceBuilder.build(get(), ITheMovieDBService::class.java)
        }
    }
}