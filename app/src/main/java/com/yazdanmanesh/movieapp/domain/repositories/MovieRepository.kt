package com.yazdanmanesh.movieapp.domain.repositories

import com.yazdanmanesh.movieapp.data.local.entities.MovieDetailsEntity
import com.yazdanmanesh.movieapp.data.local.entities.MovieEntity
import com.yazdanmanesh.movieapp.data.remote.models.MovieDetails
import com.yazdanmanesh.movieapp.data.remote.models.MovieDto
import com.yazdanmanesh.movieapp.data.remote.models.response.MovieListResponseDto

interface MovieRepository {
    suspend fun getMovies(categoryName: String): MovieListResponseDto
    suspend fun getCachedMovies(): List<MovieEntity>
    suspend fun getCachedDetailMovie(id: String): MovieDetailsEntity?
    suspend fun getMovie(id: String): MovieDetails
    suspend fun getMoviesFromDatabase(): List<MovieEntity>
    suspend fun saveListToCache(response: List<MovieDto>)
    suspend fun saveMovieDetailsToCache(response: MovieDetails)
    suspend fun deleteMoviesFromDatabase()
}
