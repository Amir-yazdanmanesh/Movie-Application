package com.yazdanmanesh.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yazdanmanesh.movieapp.data.local.entities.MovieDetailsEntity
import com.yazdanmanesh.movieapp.data.local.entities.MovieEntity

@Database(
    entities = [MovieEntity::class, MovieDetailsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DatabaseConverters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract val dao: MovieDao
}
