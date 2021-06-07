package com.pras.submission1jetpack.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pras.submission1jetpack.databinding.MovieListItemBinding
import com.bumptech.glide.Glide
import com.pras.submission1jetpack.model.local.entity.MovieListEntity

class MovieAdapter :
    PagedListAdapter<MovieListEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieListEntity>() {
            override fun areItemsTheSame(
                oldItem: MovieListEntity,
                newItem: MovieListEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieListEntity,
                newItem: MovieListEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClickCallback(data: MovieListEntity)
    }

    inner class MovieViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieListEntity) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClickCallback(movie)
            }

            binding.apply {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/original/" + movie.poster_path)
                    .centerCrop()
                    .into(imgPoster)

                tvMoviewTitle.text = movie.original_title
                tvRating.text = "Rating: " + movie.vote_average.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieListItemBinding =
            MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(movieListItemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

}