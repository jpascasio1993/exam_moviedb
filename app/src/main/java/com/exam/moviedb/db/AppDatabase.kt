package com.exam.moviedb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.exam.moviedb.db.dao.MovieDAO
import com.exam.moviedb.data.domain.MovieDTO

@Database(
    entities = [MovieDTO::class],
    version = 5
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDAO(): MovieDAO

    companion object {
        @Volatile private var instance: AppDatabase ? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: build(context).also { instance = it }
        }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "movie.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}