package com.exam.moviedb.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "movie_list_info")
data class MovieListInfo(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "page") val page: Int,
    @ColumnInfo(name = "total_pages") val totalPages: Int,
    @ColumnInfo(name = "total_results") val totalResults: Int,
    @Relation(
        parentColumn = "page",
        entityColumn = "page",
        entity = MovieResult::class
    ) val results: List<MovieResult>
)