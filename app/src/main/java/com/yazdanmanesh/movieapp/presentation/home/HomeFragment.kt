package com.yazdanmanesh.movieapp.presentation.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yazdanmanesh.movieapp.common.base.ViewBindingFragment
import com.yazdanmanesh.movieapp.common.extension.observeInLifecycle
import com.yazdanmanesh.movieapp.common.utils.UiText
import com.yazdanmanesh.movieapp.databinding.FragmentHomeBinding
import com.yazdanmanesh.movieapp.domain.models.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : ViewBindingFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpList()
        setupObservers()
        viewModel.getMovies()
    }

    private fun setupObservers() {
        viewModel.getViewState().observeInLifecycle(lifecycle, ::handleStateChange)
    }

    private fun handleStateChange(state: HomeViewModel.HomeViewState) {
        when (state) {
            is HomeViewModel.HomeViewState.Init -> Unit
            is HomeViewModel.HomeViewState.Loading -> handleLoading(state.isLoading)
            is HomeViewModel.HomeViewState.Success -> handleSuccess(state.data)
            is HomeViewModel.HomeViewState.SuccessWithEmptyData -> Unit
            is HomeViewModel.HomeViewState.Error -> handleError(state.error)
        }
    }

    private fun handleSuccess(list: List<Movie>) = adapter.setItems(list)

    private fun handleLoading(loading: Boolean) {
        binding.progressBar.isVisible = loading
    }

    private fun handleError(error: UiText) =
        Toast.makeText(requireContext(), error.asString(requireContext()), Toast.LENGTH_SHORT)
            .show()

    private fun setUpList() {
        binding.rvMovies.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )

        adapter = MovieAdapter(object : MovieItemListener {
            override fun onMovieClicked(movieId: String) {
                val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movieId)
                findNavController().navigate(action)
            }
        })
        binding.rvMovies.adapter = adapter
    }

    override fun onDestroyView() {
        binding.rvMovies.adapter = null
        super.onDestroyView()
    }
}
