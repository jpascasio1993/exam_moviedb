package com.exam.moviedb.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.exam.moviedb.data.domain.MovieDTO
import com.exam.moviedb.db.entity.MovieListInfo

@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieListInfo(movies: List<MovieDTO>)

    @Query("select * from movies where page=:page order by vote_average desc, popularity asc")
    fun fetchMovies(page: Int): List<MovieDTO>

    @Query("select COUNT(*) from movies where page=:page order by vote_average desc, popularity asc")
    fun getMoviesCount(page: Int): Int

}