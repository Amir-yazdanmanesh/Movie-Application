package com.yazdanmanesh.movieapp.data.repositories

import com.yazdanmanesh.movieapp.data.local.MovieDao
import com.yazdanmanesh.movieapp.data.local.entities.MovieDetailsEntity
import com.yazdanmanesh.movieapp.data.local.entities.MovieEntity
import com.yazdanmanesh.movieapp.data.remote.apiservices.MovieApiService
import com.yazdanmanesh.movieapp.data.remote.models.MovieDetails
import com.yazdanmanesh.movieapp.data.remote.models.MovieDto
import com.yazdanmanesh.movieapp.data.remote.models.response.MovieListResponseDto
import com.yazdanmanesh.movieapp.domain.mappers.MovieMapper
import com.yazdanmanesh.movieapp.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApiService,
    private val dao: MovieDao,
    private val mapper: MovieMapper
) : MovieRepository {
    override suspend fun getMovies(categoryName: String): MovieListResponseDto = api.getMovies(categoryName)
    override suspend fun getMovie(id: String): MovieDetails = api.getMovieDetail(id)
    override suspend fun getCachedMovies(): List<MovieEntity> = dao.getMovies()
    override suspend fun getCachedDetailMovie(id: String): MovieDetailsEntity? = dao.getMovieDetail(id)
    override suspend fun getMoviesFromDatabase(): List<MovieEntity> = dao.getMovies()
    override suspend fun saveListToCache(response: List<MovieDto>) =
        dao.insertMovies(response.map { mapper.fromDtoToEntity(it) })

    override suspend fun saveMovieDetailsToCache(response: MovieDetails) =
        dao.insertMovieDetail(mapper.fromDetailToEntity(response))

    override suspend fun deleteMoviesFromDatabase() = dao.deleteAllMovies()

}
