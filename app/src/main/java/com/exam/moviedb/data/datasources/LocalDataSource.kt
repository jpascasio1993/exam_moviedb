package com.exam.moviedb.data.datasources

import com.exam.moviedb.data.domain.Movie
import com.exam.moviedb.data.domain.MovieDTO
import com.exam.moviedb.data.domain.Result
import com.exam.moviedb.db.dao.MovieDAO
import okio.IOException

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
                    it.voteCount,
                    it.page
                )
            }
            Result.Success(movies)
        }catch(e: Exception) {
            Result.Error(IOException(e.message, e))
        }
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