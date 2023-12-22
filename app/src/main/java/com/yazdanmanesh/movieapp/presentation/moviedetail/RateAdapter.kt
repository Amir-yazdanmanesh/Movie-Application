package com.yazdanmanesh.movieapp.presentation.moviedetail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yazdanmanesh.movieapp.data.remote.models.Rating
import com.yazdanmanesh.movieapp.databinding.ListItemRateBinding

class RateAdapter : RecyclerView.Adapter<ViewModel>() {

    private val data = ArrayList<Rating>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel {
        val binding = ListItemRateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewModel(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(castList: List<Rating>) {
        this.data.clear()
        this.data.addAll(castList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewModel, position: Int) = with(holder) {
        bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}

class ViewModel(
    private val binding: ListItemRateBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Rating) {
        binding.tvSourceName.text = item.source
        binding.tvRate.text = item.value
    }
}
