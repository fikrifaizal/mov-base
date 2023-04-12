package com.sinau.movbase.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sinau.movbase.R
import com.sinau.movbase.core.domain.model.Movie
import com.sinau.movbase.databinding.MovieItemBinding
import com.sinau.movbase.BuildConfig as BC

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false))


    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listData[position]
        holder.bind(movie)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = MovieItemBinding.bind(itemView)

        fun bind(movie: Movie) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(BC.POSTER_URL + movie.posterPath)
                    .centerCrop()
                    .into(ivPoster)
                tvTitle.text = movie.title
                tvDate.text = movie.releaseDate
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}