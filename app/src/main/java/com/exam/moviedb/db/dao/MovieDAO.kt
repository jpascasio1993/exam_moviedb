package com.exam.moviedb.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.exam.moviedb.db.entity.MovieListInfo

@Dao
interface MovieDAO {

    fun insertMovieListInfo(movieListInfo: MovieListInfo) {
        val movieInfo = movieListInfo.copy(results = movieListInfo.results.map { it.copy(page = movieListInfo.page)})
        insertMovieListInfoInternal(movieInfo)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieListInfoInternal(movieListInfo: MovieListInfo)

    @Transaction
    @Query("select * from movie_list_info limit 1 offset :offset")
    fun getMovieListInfo(offset: Int): LiveData<MovieListInfo>


}