package com.yazdanmanesh.movieapp.usecases.home

import com.google.common.truth.Truth.assertThat
import com.yazdanmanesh.movieapp.utils.MockHelper
import com.yazdanmanesh.movieapp.common.utils.Resource
import com.yazdanmanesh.movieapp.common.utils.UiText
import com.yazdanmanesh.movieapp.domain.mappers.MovieMapper
import com.yazdanmanesh.movieapp.domain.repositories.MovieRepository
import com.yazdanmanesh.movieapp.domain.usecases.home.GetMoviesUseCase
import com.yazdanmanesh.movieapp.domain.usecases.home.GetMoviesUseCaseImpl
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetMoviesUseCaseTest {
    private lateinit var getMoviesUseCase: GetMoviesUseCase

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var mapper: MovieMapper
    private val categoryName: String = "batman"

    @Before
    fun setUp() {
        getMoviesUseCase = GetMoviesUseCaseImpl(
            movieRepository,
            mapper
        )
    }

    @Test
    fun `check getMovies() success case`() = runBlocking {
        // when
        whenever(movieRepository.getMovies(categoryName)).thenAnswer { MockHelper.movieListDto }
        val result = getMoviesUseCase.getMovies()
        val flowList = result.toList()
        // then
        assertThat(flowList[0]).isInstanceOf(Resource.Loading::class.java)
        assertThat(flowList[1]).isInstanceOf(Resource.Success::class.java)
    }

    @Test
    fun `check getMovies() http exception error case`() = runBlocking {
        // when
        whenever(movieRepository.getMovies(categoryName)).thenAnswer { throw MockHelper.getHttpException() }
        val result = getMoviesUseCase.getMovies()
        val flowList = result.toList()
        // then
        assertThat(flowList[0]).isInstanceOf(Resource.Loading::class.java)
        assertThat(flowList[1]).isInstanceOf(Resource.Error::class.java)
        assertThat(flowList[1].message).isInstanceOf(UiText.DynamicString::class.java)
    }

    @Test
    fun `check getMovies() io exception error case`() = runBlocking {
        // when
        whenever(movieRepository.getMovies(categoryName)).thenAnswer { throw MockHelper.ioException }
        val result = getMoviesUseCase.getMovies()
        val flowList = result.toList()
        // then

        assertThat(flowList[0]).isInstanceOf(Resource.Loading::class.java)
        assertThat(flowList[1]).isInstanceOf(Resource.Error::class.java)
        assertThat(flowList[1].message).isInstanceOf(UiText.StringResource::class.java)
    }
}
