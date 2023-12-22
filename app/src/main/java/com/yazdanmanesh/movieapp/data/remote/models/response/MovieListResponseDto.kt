package com.yazdanmanesh.movieapp.data.remote.models.response

import com.google.gson.annotations.SerializedName
import com.yazdanmanesh.movieapp.data.remote.models.MovieDto

data class MovieListResponseDto(
    @SerializedName("Search")
    val search: List<MovieDto>,
    val response: Boolean,
    val totalResults: Int
)

