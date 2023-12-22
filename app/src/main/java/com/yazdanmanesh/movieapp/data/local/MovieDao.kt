package com.yazdanmanesh.movieapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yazdanmanesh.movieapp.data.local.entities.MovieDetailsEntity
import com.yazdanmanesh.movieapp.data.local.entities.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM Movies")
    suspend fun getMovies(): List<MovieEntity>?

    @Query("DELETE FROM Movies")
    suspend fun deleteAllMovies()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movies: MovieDetailsEntity)

    @Query("SELECT * FROM MovieDetails where imdbID = :id")
    suspend fun getMovieDetail(id:String): MovieDetailsEntity?

}
