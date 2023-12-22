package com.yazdanmanesh.movieapp.domain.usecases.home

import com.yazdanmanesh.movieapp.R
import com.yazdanmanesh.movieapp.common.extension.handleError
import com.yazdanmanesh.movieapp.common.utils.Resource
import com.yazdanmanesh.movieapp.common.utils.UiText
import com.yazdanmanesh.movieapp.data.local.entities.MovieEntity
import com.yazdanmanesh.movieapp.domain.mappers.MovieMapper
import com.yazdanmanesh.movieapp.domain.models.Movie
import com.yazdanmanesh.movieapp.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesUseCaseImpl @Inject constructor(
    private val repository: MovieRepository,
    private val mapper: MovieMapper
) :
    GetMoviesUseCase {
    override suspend fun getMovies(): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val cachedMovies: List<MovieEntity> = repository.getCachedMovies()
            if (cachedMovies.isNotEmpty()) {
                emit(Resource.Success(data = cachedMovies.map(mapper::fromEntityToDomain)))
                return@flow
            }

            val response = repository.getMovies(categoryName = "batman").search
            repository.saveListToCache(response)
            emit(Resource.Success(data = response.map(mapper::fromDtoToDomain)))
        } catch (e: HttpException) {
            emit(Resource.Error(e.handleError()))
        } catch (e: IOException) {
            emit(Resource.Error(UiText.StringResource(R.string.couldntReachServerError)))
        }
    }
}
