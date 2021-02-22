package com.exam.moviedb.data.domain

import androidx.room.*
import com.exam.moviedb.db.utils.ListIntDataConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieDTO(
    @SerializedName("adult") @ColumnInfo(name = "adult") val adult: Boolean,
    @SerializedName("backdrop_path") @ColumnInfo(name = "backdrop_path") val backdropPath: String,
    @field:TypeConverters(ListIntDataConverter::class) @ColumnInfo(name = "genre_ids") val genreIds: List<Int> = listOf(),
    @SerializedName("id") @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false) val id: Int,
    @SerializedName("original_language") @ColumnInfo(name = "original_language") val originalLanguage: String,
    @SerializedName("original_title") @ColumnInfo(name = "original_title") val originalTitle: String,
    @SerializedName("overview") @ColumnInfo(name = "overview") val overview: String,
    @SerializedName("popularity") @ColumnInfo(name = "popularity") val popularity: Int,
    @SerializedName("poster_path") @ColumnInfo(name = "poster_path") val posterPath: String,
    @SerializedName("release_date") @ColumnInfo(name = "release_date") val releaseDate: String,
    @SerializedName("title") @ColumnInfo(name = "title") val title: String,
    @SerializedName("video") @ColumnInfo(name = "video") val video: Boolean,
    @SerializedName("vote_average") @ColumnInfo(name = "vote_average") val voteAverage: Float,
    @SerializedName("vote_count") @ColumnInfo(name = "vote_count") val voteCount: Int,
    @ColumnInfo(name = "page") val page: Int
)