package com.yazdanmanesh.movieapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movies")
data class MovieEntity(
    @PrimaryKey val imdbID: String,
    val year: String,
    val title: String,
    val type: String,
    val poster: String?,
)
