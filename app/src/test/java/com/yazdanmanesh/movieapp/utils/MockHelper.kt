package com.yazdanmanesh.movieapp.utils

import com.yazdanmanesh.movieapp.common.utils.EMPTY_STRING
import com.yazdanmanesh.movieapp.data.remote.models.MovieDetails
import com.yazdanmanesh.movieapp.data.remote.models.MovieDto
import com.yazdanmanesh.movieapp.data.remote.models.Rating
import com.yazdanmanesh.movieapp.data.remote.models.response.MovieListResponseDto
import com.yazdanmanesh.movieapp.domain.mappers.MovieMapper
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class MockHelper {
    companion object {
        private const val errorJson = "{\"error\":\"\"}"
        val ioException = IOException()
        val movieDto = MovieDto(
            imdbID = EMPTY_STRING,
            poster = EMPTY_STRING,
            title = EMPTY_STRING,
            type = EMPTY_STRING,
            year = EMPTY_STRING,
        )

        val emptyMovieDetails = MovieDetails(
            title = EMPTY_STRING,
            year = EMPTY_STRING,
            rated = EMPTY_STRING,
            released = EMPTY_STRING,
            runtime = EMPTY_STRING,
            genre = EMPTY_STRING,
            director = EMPTY_STRING,
            writer = EMPTY_STRING,
            actors = EMPTY_STRING,
            plot = EMPTY_STRING,
            language = EMPTY_STRING,
            country = EMPTY_STRING,
            awards = EMPTY_STRING,
            poster = EMPTY_STRING,
            ratings = emptyList(),
            metascore = EMPTY_STRING,
            imdbRating = EMPTY_STRING,
            imdbVotes = EMPTY_STRING,
            imdbID = EMPTY_STRING,
            type = EMPTY_STRING,
            dvd = EMPTY_STRING,
            boxOffice = EMPTY_STRING,
            production = EMPTY_STRING,
            website = EMPTY_STRING,
            response = EMPTY_STRING
        )

        val emptyRatingMock = Rating(
            source = EMPTY_STRING,
            value = EMPTY_STRING
        )

        val movie = MovieMapper().fromDtoToDomain(movieDto)
        val movieList = arrayListOf(movie)
        val movieListDto = MovieListResponseDto(
            search = listOf(movieDto),
            totalResults = 0,
            response = true,
        )

        fun getHttpException(): HttpException {
            return HttpException(
                Response.error<ResponseBody>(
                    500,
                    errorJson.toResponseBody("plain/text".toMediaTypeOrNull())
                )
            )
        }
    }
}
