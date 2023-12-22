package com.yazdanmanesh.movieapp.domain.mappers

import com.yazdanmanesh.movieapp.common.utils.EMPTY_STRING
import com.yazdanmanesh.movieapp.data.local.entities.MovieDetailsEntity
import com.yazdanmanesh.movieapp.data.local.entities.MovieEntity
import com.yazdanmanesh.movieapp.data.remote.models.MovieDetails
import com.yazdanmanesh.movieapp.data.remote.models.MovieDto
import com.yazdanmanesh.movieapp.domain.models.Movie

class MovieMapper {

    fun fromDtoToEntity(movieDto: MovieDto): MovieEntity = with(movieDto) {
        MovieEntity(
            imdbID = imdbID,
            poster = poster ?: EMPTY_STRING,
            type = type,
            year = year,
            title = title,
        )
    }

    fun fromDetailToEntity(movieDetails: MovieDetails): MovieDetailsEntity = with(movieDetails) {
        MovieDetailsEntity(
            imdbID = imdbID,
            poster = poster,
            type = type,
            year = year,
            title = title,
            actors = actors,
            awards = awards,
            boxOffice = boxOffice,
            country = country,
            director = director,
            dvd = dvd,
            genre = genre,
            imdbRating = imdbRating,
            imdbVotes = imdbVotes,
            language = language,
            metascore = metascore,
            plot = plot,
            production = production,
            rated = rated,
            ratings = ratings,
            released = released,
            response = response,
            runtime = runtime,
            website = website,
            writer = writer
        )
    }

    fun fromDtoToDomain(movieDto: MovieDto): Movie = with(movieDto) {
        Movie(
            imdbID = imdbID,
            poster = poster ?: EMPTY_STRING,
            type = type,
            year = year,
            title = title,
        )
    }

    fun fromEntityToDomain(movieDetails: MovieDetailsEntity): MovieDetails = with(movieDetails) {
        MovieDetails(
            imdbID = imdbID,
            poster = poster,
            type = type,
            year = year,
            title = title,
            actors = actors,
            awards = awards,
            boxOffice = boxOffice ?: EMPTY_STRING,
            country = country,
            director = director,
            dvd = dvd ?: EMPTY_STRING,
            genre = genre,
            imdbRating = imdbRating,
            imdbVotes = imdbVotes,
            language = language,
            metascore = metascore,
            plot = plot,
            production = production ?: EMPTY_STRING,
            rated = rated,
            ratings = ratings,
            released = released,
            response = response ?: EMPTY_STRING,
            runtime = runtime,
            website = website ?: EMPTY_STRING,
            writer = writer
        )
    }

    fun fromEntityToDomain(movieEntity: MovieEntity): Movie = with(movieEntity) {
        Movie(
            imdbID = imdbID,
            poster = poster ?: EMPTY_STRING,
            type = type,
            year = year,
            title = title,
        )
    }
}
