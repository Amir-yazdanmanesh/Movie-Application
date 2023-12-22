package com.yazdanmanesh.movieapp.data.remote.models

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val imdbID: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Poster")
    val poster: String?,
)
