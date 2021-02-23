package com.exam.moviedb.di

import android.content.Context
import com.exam.moviedb.R
import com.exam.moviedb.data.datasources.ILocalDataSource
import com.exam.moviedb.data.datasources.IRemoteDataSource
import com.exam.moviedb.data.datasources.LocalDataSource
import com.exam.moviedb.data.datasources.RemoteDataSource
import com.exam.moviedb.data.domain.ApiKey
import com.exam.moviedb.data.domain.MovieDTO
import com.exam.moviedb.data.repository.IMovieRepository
import com.exam.moviedb.data.repository.MovieRepositoryImpl
import com.exam.moviedb.data.services.ITheMovieDBService
import com.exam.moviedb.data.services.ServiceBuilder
import com.exam.moviedb.db.AppDatabase
import com.exam.moviedb.ui.movie.MovieViewModel
import com.exam.moviedb.utils.gson.MovieDeserializer
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DIModule {
    val appModules = module {

        single {
            AppDatabase(get())
        }

        single {
            get<AppDatabase>().movieDAO()
        }

        single<ILocalDataSource> {
            LocalDataSource(get())
        }

        single<IRemoteDataSource> {
            RemoteDataSource(get(), get())
        }

        single {
            OkHttpClient().newBuilder().build()
        }

        single {
            val context = get<Context>()
            ApiKey(context.getString(R.string.movie_api_key))
        }

        single<IMovieRepository> {
            MovieRepositoryImpl(get(), get())
        }

        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .client(get())
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().registerTypeAdapter(
                            TypeToken.getParameterized(List::class.java, MovieDTO::class.java).type,
                            MovieDeserializer()
                        ).setLenient().create()
                    )
                )
                .build()
        }

        single {
            ServiceBuilder.build(get(), ITheMovieDBService::class.java)
        }

        single {
            MovieViewModel(get())
        }
    }
}