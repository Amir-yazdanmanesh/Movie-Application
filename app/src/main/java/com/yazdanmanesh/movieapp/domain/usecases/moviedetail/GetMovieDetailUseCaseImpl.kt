package com.yazdanmanesh.movieapp.domain.usecases.moviedetail

import com.yazdanmanesh.movieapp.R
import com.yazdanmanesh.movieapp.common.extension.handleError
import com.yazdanmanesh.movieapp.common.utils.Resource
import com.yazdanmanesh.movieapp.common.utils.UiText
import com.yazdanmanesh.movieapp.data.remote.models.MovieDetails
import com.yazdanmanesh.movieapp.domain.mappers.MovieMapper
import com.yazdanmanesh.movieapp.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailUseCaseImpl @Inject constructor(
    private val repository: MovieRepository,
    private val mapper: MovieMapper
) : GetMovieDetailUseCase {
    override suspend fun getMovieById(id: String): Flow<Resource<MovieDetails>> = flow {
        try {
            emit(Resource.Loading())
            val cachedMovie = repository.getCachedDetailMovie(id)
            cachedMovie?.let {
                emit(Resource.Success(data = mapper.fromEntityToDomain(cachedMovie)))
                return@flow
            }

            val response = repository.getMovie(id)
            repository.saveMovieDetailsToCache(response)

            emit(Resource.Success(data = response))
        } catch (e: HttpException) {
            emit(Resource.Error(e.handleError()))
        } catch (e: IOException) {
            emit(Resource.Error(UiText.StringResource(R.string.couldntReachServerError)))
        }
    }
}
