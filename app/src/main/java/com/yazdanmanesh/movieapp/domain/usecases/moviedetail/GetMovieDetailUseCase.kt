package com.yazdanmanesh.movieapp.domain.usecases.moviedetail

import com.yazdanmanesh.movieapp.common.utils.Resource
import com.yazdanmanesh.movieapp.data.remote.models.MovieDetails
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailUseCase {
    suspend fun getMovieById(id: String): Flow<Resource<MovieDetails>>
}
