package com.yazdanmanesh.movieapp.di

import com.yazdanmanesh.movieapp.domain.usecases.home.GetMoviesUseCaseImpl
import com.yazdanmanesh.movieapp.domain.usecases.home.GetMoviesUseCase
import com.yazdanmanesh.movieapp.domain.usecases.moviedetail.GetMovieDetailUseCase
import com.yazdanmanesh.movieapp.domain.usecases.moviedetail.GetMovieDetailUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    @Singleton
    abstract fun bindGetMoviesUseCase(
        getPopularMoviesUseCaseImpl: GetMoviesUseCaseImpl
    ): GetMoviesUseCase

    @Binds
    @Singleton
    abstract fun bindGetMovieDetailUseCase(
        getMovieDetailUseCaseImpl: GetMovieDetailUseCaseImpl
    ): GetMovieDetailUseCase

}
