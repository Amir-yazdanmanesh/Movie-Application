package com.yazdanmanesh.movieapp.presentation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yazdanmanesh.movieapp.databinding.ListItemMovieBinding
import com.yazdanmanesh.movieapp.domain.models.Movie

class MovieAdapter(
    private val listener: MovieItemListener
) : RecyclerView.Adapter<ViewModel>() {

    private val data = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        val binding = ListItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewModel(binding, listener)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<Movie>) {
        this.data.clear()
        this.data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewModel, position: Int) = with(holder) {
        bind(
            data[position]
        )
    }

    override fun getItemCount(): Int = data.size
}

class ViewModel(
    private val binding: ListItemMovieBinding,
    private val listener: MovieItemListener
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var movie: Movie

    init {
        binding.root.setOnClickListener(this)
    }

    fun bind(item: Movie) {
        this.movie = item
        binding.tvTitle.text = "${item.title} (${item.year})"
        Glide.with(itemView.context).load(item.poster).into(binding.ivMovie)
    }

    override fun onClick(v: View?) = listener.onMovieClicked(movie.imdbID)
}

interface MovieItemListener {
    fun onMovieClicked(movieId: String)
}
