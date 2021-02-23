package com.exam.moviedb.data.repository

import android.util.Log
import com.exam.moviedb.data.datasources.ILocalDataSource
import com.exam.moviedb.data.datasources.IRemoteDataSource
import com.exam.moviedb.data.domain.Movie
import com.exam.moviedb.data.domain.Result
import java.io.IOException
import java.lang.Exception

class MovieRepositoryImpl(private val localSource: ILocalDataSource, private val remoteSource: IRemoteDataSource):
    IMovieRepository {
    override suspend fun getDataOnline(page: Int): Result<Unit> {
        return try {
            val listRemote = remoteSource.fetchMovies(page)
            localSource.insertMovies(listRemote)
        }catch(e: Exception){
            Result.Error(IOException(e.message, e))
        }
    }

    override suspend fun getMovieList(page: Int): Result<List<Movie>> {
        val count = getMovieCount(page)

        if(count == 0) {
            getDataOnline(page)
        }
        return localSource.getMovies(page)
    }

    override fun getMovieCount(page: Int): Int {
        return localSource.getMoviesCount(page)
    }
}