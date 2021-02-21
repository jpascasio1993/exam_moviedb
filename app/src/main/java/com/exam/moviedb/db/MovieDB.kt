package com.exam.moviedb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.exam.moviedb.db.entity.MovieListInfo
import com.exam.moviedb.db.entity.MovieResult

@Database(
    entities = [MovieListInfo::class, MovieResult::class],
    version = 1
)
abstract class MovieDB : RoomDatabase() {
    companion object {
        @Volatile private var instance: MovieDB ? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: build(context).also { instance = it }
        }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, MovieDB::class.java, "movie.db")
                .build()
    }
}