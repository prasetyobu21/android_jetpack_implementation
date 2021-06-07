package com.pras.submission1jetpack.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pras.submission1jetpack.databinding.MovieListItemBinding
import com.pras.submission1jetpack.model.local.entity.TvListEntity

class FavTvAdapter():PagedListAdapter<TvListEntity, FavTvAdapter.TvViewHodler>(DIFF_CALLBACK) {
    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvListEntity>(){
            override fun areItemsTheSame(oldItem: TvListEntity, newItem: TvListEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvListEntity, newItem: TvListEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    private var onItemClickCallback: FavTvAdapter.OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback{
        fun onItemClickCallback(data:TvListEntity)
    }

    inner class TvViewHodler(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvListEntity) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClickCallback(tvShow)
            }

            binding.apply {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/original/" + tvShow.poster_path)
                    .centerCrop()
                    .into(imgPoster)

                tvMoviewTitle.text = tvShow.name
                tvRating.text = "Rating: " + tvShow.vote_average.toString()
            }
        }
    }

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTvAdapter.TvViewHodler {
        val movieListItemBinding = MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHodler(movieListItemBinding)
    }

    override fun onBindViewHolder(holder: FavTvAdapter.TvViewHodler, position: Int) {
        val tv = getItem(position)
        if (tv!=null){
            holder.bind(tv)
        }
    }
}