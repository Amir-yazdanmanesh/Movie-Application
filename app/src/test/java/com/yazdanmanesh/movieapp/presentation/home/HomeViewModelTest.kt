package com.yazdanmanesh.movieapp.presentation.home

import com.google.common.truth.Truth.assertThat
import com.yazdanmanesh.movieapp.utils.MockHelper
import com.yazdanmanesh.movieapp.common.utils.Resource
import com.yazdanmanesh.movieapp.common.utils.UiText
import com.yazdanmanesh.movieapp.domain.models.Movie
import com.yazdanmanesh.movieapp.domain.usecases.home.GetMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private val errorMessage: String = "error"

    @Mock
    private lateinit var getMoviesUseCase: GetMoviesUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = HomeViewModel(getMoviesUseCase)
    }

    @Test
    fun `getPopularMoviesUseCase emits success`() = runTest {
        whenever(getMoviesUseCase.getMovies()).thenAnswer {
            flow { emit(Resource.Success(data = MockHelper.movieList)) }
        }
        viewModel.getMovies()
        val currentState = viewModel.getViewState()
        assertThat(currentState.value).isInstanceOf(HomeViewModel.HomeViewState.Success::class.java)
    }

    @Test
    fun `getMovieUseCase emits success with empty list`() = runTest {
        whenever(getMoviesUseCase.getMovies()).thenAnswer {
            flow { emit(Resource.Success(data = arrayListOf<Movie>())) }
        }
        viewModel.getMovies()
        val currentState = viewModel.getViewState()
        assertThat(currentState.value).isInstanceOf(
            HomeViewModel.HomeViewState.SuccessWithEmptyData::class.java
        )
    }

    @Test
    fun `getMoviesUseCase emits error`() = runTest {
        whenever(getMoviesUseCase.getMovies()).thenAnswer {
            flow<Resource<Any>> {
                emit(
                    Resource.Error(message = UiText.DynamicString(value = errorMessage))
                )
            }
        }
        viewModel.getMovies()
        val currentState = viewModel.getViewState()
        assertThat(currentState.value).isInstanceOf(HomeViewModel.HomeViewState.Error::class.java)
    }

    @Test
    fun `getMoviesUseCase emits loading`() = runTest {
        whenever(getMoviesUseCase.getMovies()).thenAnswer {
            flow<Resource<Any>> { emit(Resource.Loading()) }
        }
        viewModel.getMovies()
        val currentState = viewModel.getViewState()
        assertThat(currentState.value).isInstanceOf(HomeViewModel.HomeViewState.Loading::class.java)
    }

    @Test
    fun `verify getMoviesUseCase called with correct parameter`() = runTest {
        whenever(getMoviesUseCase.getMovies()).thenAnswer {
            flow<Resource<Any>> { emit(Resource.Loading()) }
        }
        viewModel.getMovies()
        verify(getMoviesUseCase).getMovies()
    }

    @Test
    fun `verify setLoading function called with isLoading=true `() = runTest {
        viewModel.setLoading(true)
        val currentState = viewModel.getViewState()
        assertThat(currentState.value).isInstanceOf(HomeViewModel.HomeViewState.Loading::class.java)
        val loadingState = currentState.value as HomeViewModel.HomeViewState.Loading
        assertThat(loadingState.isLoading).isEqualTo(true)
    }

    @Test
    fun `verify setLoading function called with isLoading=false `() = runTest {
        viewModel.setLoading(false)
        val currentState = viewModel.getViewState()
        assertThat(currentState.value).isInstanceOf(HomeViewModel.HomeViewState.Loading::class.java)
        val loadingState = currentState.value as HomeViewModel.HomeViewState.Loading
        assertThat(loadingState.isLoading).isEqualTo(false)
    }

    @After
    fun tearDown() = Dispatchers.resetMain()
}
