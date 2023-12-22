package com.yazdanmanesh.movieapp.data.remote.apiservices

import com.yazdanmanesh.movieapp.data.remote.models.MovieDetails
import com.yazdanmanesh.movieapp.data.remote.models.response.MovieListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET(".")
    suspend fun getMovies(@Query("s") categoryName: String): MovieListResponseDto

    @GET(".")
    suspend fun getMovieDetail(@Query("i") id: String): MovieDetails
}
