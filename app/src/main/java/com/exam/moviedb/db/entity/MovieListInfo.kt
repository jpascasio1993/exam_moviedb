package com.exam.moviedb.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.exam.moviedb.data.domain.MovieDTO

@Entity(tableName = "movie_list_info")
data class MovieListInfo(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "page") val page: Int,
    @ColumnInfo(name = "total_pages") val totalPages: Int,
    @ColumnInfo(name = "total_results") val totalResults: Int,
    @Relation(
        parentColumn = "page",
        entityColumn = "page",
        entity = MovieDTO::class
    ) val results: List<MovieDTO>
)