package com.yazdanmanesh.movieapp.presentation.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yazdanmanesh.movieapp.R
import com.yazdanmanesh.movieapp.common.utils.Resource
import com.yazdanmanesh.movieapp.common.utils.UiText
import com.yazdanmanesh.movieapp.data.remote.models.MovieDetails
import com.yazdanmanesh.movieapp.domain.usecases.moviedetail.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<MovieDetailViewState>(MovieDetailViewState.Init)
    fun getViewState(): StateFlow<MovieDetailViewState> = _state.asStateFlow()

    fun getMovieDetails(id: String) {
        viewModelScope.launch {
            getMovieDetailUseCase.getMovieById(id).collectLatest { movie ->
                if (movie is Resource.Loading) {
                    _state.value = MovieDetailViewState.Loading
                } else if (movie is Resource.Error) {
                    _state.value = MovieDetailViewState.Error(
                        UiText.StringResource(R.string.movieDetailPage_emptyError)
                    )
                } else if (movie is Resource.Success) {
                    if (movie.data == null) {
                        _state.value = MovieDetailViewState.Error(
                            UiText.StringResource(R.string.movieDetailPage_emptyError)
                        )
                    } else {
                        _state.value =
                            MovieDetailViewState.Success(movie = movie.data)
                    }
                }
            }
        }
    }

    sealed class MovieDetailViewState {
        object Init : MovieDetailViewState()
        object Loading : MovieDetailViewState()
        data class Success(val movie: MovieDetails) : MovieDetailViewState()
        data class Error(val error: UiText) : MovieDetailViewState()
    }
}
