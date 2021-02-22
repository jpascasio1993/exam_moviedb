package com.exam.moviedb.data.datasources

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.exam.moviedb.data.domain.Movie
import com.exam.moviedb.data.domain.MovieDTO
import com.exam.moviedb.data.domain.Result
import com.exam.moviedb.db.dao.MovieDAO
import okio.IOException
import java.lang.Exception

class LocalDataSource(private val movieDAO: MovieDAO) : ILocalDataSource {

    override fun getMovies(page: Int): Result<List<Movie>> {
        return try {
            val movies = movieDAO.fetchMovies(page).map {
                Movie(
                    it.backdropPath,
                    it.genreIds,
                    it.id,
                    it.overview,
                    it.popularity,
                    it.posterPath,
                    it.releaseDate,
                    it.title,
                    it.video,
                    it.voteAverage,
                    it.page
                )
            }
            Result.Success(movies)
        }catch(e: Exception) {
            Result.Error(IOException(e.message, e))
        }
//        return Transformations.map(movies) { itMain ->
//            itMain.map {
//                Movie(
//                    it.backdropPath,
//                    it.genreIds,
//                    it.id,
//                    it.overview,
//                    it.popularity,
//                    it.posterPath,
//                    it.releaseDate,
//                    it.title,
//                    it.video,
//                    it.voteCount,
//                    it.page
//                )
//            }
//        }
    }

    override suspend fun insertMovies(movies: List<MovieDTO>): Result<Unit> {
        return try {
            movieDAO.insertMovieListInfo(movies)
            Result.Success(Unit)
        }catch (e: Exception) {
            Result.Error(IOException(e.message, e))
        }
    }

    override fun getMoviesCount(page: Int): Int {
        return movieDAO.getMoviesCount(page)
    }


}