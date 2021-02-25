package com.exam.moviedb.data.domain

import androidx.room.*
import com.exam.moviedb.db.utils.ListIntDataConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieDTO(
    @SerializedName("adult") @ColumnInfo(name = "adult") val adult: Boolean,
    @SerializedName("backdrop_path") @ColumnInfo(name = "backdrop_path", defaultValue = "N/A") val backdropPath: String,
    @Expose(
        serialize = true,
        deserialize = false
    ) @field:TypeConverters(ListIntDataConverter::class) @ColumnInfo(name = "genre_ids") val genreIds: List<Int> = emptyList(),
    @SerializedName("id") @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = false) val id: Int,
    @SerializedName("original_language") @ColumnInfo(name = "original_language", defaultValue = "N/A") val originalLanguage: String,
    @SerializedName("original_title") @ColumnInfo(name = "original_title", defaultValue = "N/A") val originalTitle: String,
    @SerializedName("overview") @ColumnInfo(name = "overview", defaultValue = "N/A") val overview: String,
    @SerializedName("popularity") @ColumnInfo(name = "popularity") val popularity: Float,
    @SerializedName("poster_path") @ColumnInfo(name = "poster_path", defaultValue = "N/A") val posterPath: String,
    @SerializedName("release_date") @ColumnInfo(name = "release_date", defaultValue = "N/A") val releaseDate: String,
    @SerializedName("title") @ColumnInfo(name = "title", defaultValue = "N/A") val title: String,
    @SerializedName("video") @ColumnInfo(name = "video") val video: Boolean,
    @SerializedName("vote_average") @ColumnInfo(name = "vote_average") val voteAverage: Float,
    @SerializedName("vote_count") @ColumnInfo(name = "vote_count") val voteCount: Int,
    @ColumnInfo(name = "page") val page: Int
)