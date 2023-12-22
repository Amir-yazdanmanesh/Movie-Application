package com.yazdanmanesh.movieapp.domain.usecases.home

import com.yazdanmanesh.movieapp.common.utils.Resource
import com.yazdanmanesh.movieapp.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase {
    suspend fun getMovies(): Flow<Resource<List<Movie>>>
}
