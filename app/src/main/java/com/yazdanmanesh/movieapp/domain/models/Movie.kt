package com.yazdanmanesh.movieapp.domain.models

data class Movie(
    val imdbID: String,
    val year: String,
    val title: String,
    val type: String,
    val poster: String?,
)
