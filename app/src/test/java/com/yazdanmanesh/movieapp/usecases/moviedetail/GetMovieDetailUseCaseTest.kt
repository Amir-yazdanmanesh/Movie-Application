package com.yazdanmanesh.movieapp.usecases.moviedetail

import com.google.common.truth.Truth.assertThat
import com.yazdanmanesh.movieapp.utils.MockHelper
import com.yazdanmanesh.movieapp.common.utils.Resource
import com.yazdanmanesh.movieapp.common.utils.UiText
import com.yazdanmanesh.movieapp.domain.mappers.MovieMapper
import com.yazdanmanesh.movieapp.domain.repositories.MovieRepository
import com.yazdanmanesh.movieapp.domain.usecases.moviedetail.GetMovieDetailUseCase
import com.yazdanmanesh.movieapp.domain.usecases.moviedetail.GetMovieDetailUseCaseImpl
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailUseCaseTest {
    private lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var mapper: MovieMapper
    private val movieId: String = "tt0372784"

    @Before
    fun setUp() {
        getMovieDetailUseCase = GetMovieDetailUseCaseImpl(movieRepository, mapper)
    }

    @Test
    fun `check getMovie() io exception error case`() = runBlocking {
        // when
        whenever(movieRepository.getMovie(movieId)).thenAnswer { throw MockHelper.ioException }
        val result = getMovieDetailUseCase.getMovieById(movieId)
        val flowList = result.toList()
        // then

        assertThat(flowList[0]).isInstanceOf(Resource.Loading::class.java)
        assertThat(flowList[1]).isInstanceOf(Resource.Error::class.java)
        assertThat(flowList[1].message).isInstanceOf(UiText.StringResource::class.java)
    }

    @Test
    fun `check getMovie() http exception error case`() = runBlocking {
        // when
        whenever(movieRepository.getMovie(movieId)).thenAnswer { throw MockHelper.getHttpException() }
        val result = getMovieDetailUseCase.getMovieById(movieId)
        val flowList = result.toList()
        // then
        assertThat(flowList[0]).isInstanceOf(Resource.Loading::class.java)
        assertThat(flowList[1]).isInstanceOf(Resource.Error::class.java)
        assertThat(flowList[1].message).isInstanceOf(UiText.DynamicString::class.java)
    }

    @Test
    fun `check getMovie() success case`() = runBlocking {
        // when
        whenever(movieRepository.getMovie(movieId)).thenAnswer { MockHelper.emptyMovieDetails }
        val result = getMovieDetailUseCase.getMovieById(movieId)
        val flowList = result.toList()
        // then
        assertThat(flowList[0]).isInstanceOf(Resource.Loading::class.java)
        assertThat(flowList[1]).isInstanceOf(Resource.Success::class.java)
    }
}
