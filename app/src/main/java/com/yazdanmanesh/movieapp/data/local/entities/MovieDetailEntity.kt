package com.yazdanmanesh.movieapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yazdanmanesh.movieapp.data.remote.models.Rating

@Entity(tableName = "MovieDetails")
data class MovieDetailsEntity(
    @PrimaryKey val imdbID: String,
    val title: String,
    val year: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val language: String,
    val country: String,
    val awards: String,
    val poster: String,
    val ratings: List<Rating>,
    val metascore: String,
    val imdbRating: String,
    val imdbVotes: String,
    val type: String,
    val dvd: String?,
    val boxOffice: String?,
    val production: String?,
    val website: String?,
    val response: String?
)
