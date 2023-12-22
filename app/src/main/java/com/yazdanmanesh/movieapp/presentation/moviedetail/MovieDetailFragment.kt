package com.yazdanmanesh.movieapp.presentation.moviedetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yazdanmanesh.movieapp.R
import com.yazdanmanesh.movieapp.common.base.ViewBindingFragment
import com.yazdanmanesh.movieapp.common.extension.observeInLifecycle
import com.yazdanmanesh.movieapp.common.utils.UiText
import com.yazdanmanesh.movieapp.data.remote.models.MovieDetails
import com.yazdanmanesh.movieapp.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : ViewBindingFragment<FragmentMovieDetailBinding>(FragmentMovieDetailBinding::inflate) {
    private val viewModel: MovieDetailViewModel by viewModels()
    private val args: MovieDetailFragmentArgs by navArgs()
    private lateinit var rateAdapter: RateAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()
        setupObservers()
        init()
    }

    private fun setupObservers() {
        viewModel.getViewState().observeInLifecycle(lifecycle, ::handleStateChange)
    }

    private fun handleStateChange(state: MovieDetailViewModel.MovieDetailViewState) {
        when (state) {
            is MovieDetailViewModel.MovieDetailViewState.Error -> handleError(state.error)
            MovieDetailViewModel.MovieDetailViewState.Init -> MovieDetailViewModel.MovieDetailViewState.Init
            is MovieDetailViewModel.MovieDetailViewState.Loading -> handleLoading()
            is MovieDetailViewModel.MovieDetailViewState.Success -> handleSuccess(state.movie)
        }
    }

    private fun handleSuccess(data: MovieDetails) {
        binding.progress.isVisible = false
        binding.tvMovieTitle.text = data.title
        binding.tvDescription.text = data.plot
        binding.tvGenre.text = data.genre
        binding.tvDuration.text = data.runtime
        binding.tvRating.text = getString(R.string.ratingWithParam, data.imdbRating.toFloat())
        Glide.with(this).load(data.poster).into(binding.ivMovie)
        if (data.ratings.isNotEmpty()) {
            rateAdapter.setItems(data.ratings)
        }
    }

    private fun handleLoading() {
        binding.progress.isVisible = true
    }

    private fun handleError(error: UiText) {
        binding.progress.isVisible = false
        Toast.makeText(
            requireContext(),
            error.asString(requireContext()),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun init() {
        setUpList()
        viewModel.getMovieDetails(id = args.movieId)
    }

    private fun setUpList() {
        binding.rvCasts.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.HORIZONTAL,
            false
        )
        rateAdapter = RateAdapter()
        binding.rvCasts.adapter = rateAdapter
    }

    private fun listeners() = binding.ivBack.setOnClickListener { findNavController().popBackStack() }
}
