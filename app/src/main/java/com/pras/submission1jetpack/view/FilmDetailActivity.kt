package com.pras.submission1jetpack.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.pras.submission1jetpack.databinding.ActivityFilmDetailBinding
import com.pras.submission1jetpack.viewmodel.GetMoviesViewModel
import com.pras.submission1jetpack.viewmodel.GetMoviesViewModelFactory
import com.pras.submission1jetpack.viewmodel.GetTvShowViewModelFactory
import com.pras.submission1jetpack.viewmodel.GetTvShowsViewModel
import com.pras.submission1jetpack.vo.Status

class FilmDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityFilmDetailBinding

    companion object {
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_TYPE = "extra_type"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("type", intent.getStringExtra(EXTRA_TYPE).toString())

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        if (intent.getStringExtra(EXTRA_TYPE).toString() == "tvShow") {

            val id = intent.getStringExtra(EXTRA_ID)!!.toInt()

            val factory = GetTvShowViewModelFactory.getInstance(this)
            val viewModel = ViewModelProvider(this, factory)[GetTvShowsViewModel::class.java]
            viewModel.setSelectedTv(id)
            showLoading(true)
            binding.apply {
                viewModel.tv.observe(this@FilmDetailActivity, {
                    if (it != null) {
                        when (it.status) {
                            Status.LOADING -> showLoading(true)
                            Status.SUCCESS -> {
                                Glide.with(this@FilmDetailActivity)
                                    .load("https://image.tmdb.org/t/p/original/" + it.data?.poster_path)
                                    .centerCrop()
                                    .into(imgPoster)

                                tvTitle.text = it.data?.name
                                tvPlaytime.text = it.data?.first_air_date
                                tvRating.text = it.data?.vote_average.toString() + "/10"
                                tvOverview.text = it.data?.overview
                                val favoriteStatus = it.data?.favorite
                                showLoading(false)

                                Log.d("movieData", it.data.toString())

                                btnFavorite.setOnClickListener {
                                    viewModel.setFavoriteTv()
                                    if (favoriteStatus == false) {
                                        Toast.makeText(
                                            this@FilmDetailActivity,
                                            "Berhasil ditambahkan sebagai favorite",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Toast.makeText(
                                            this@FilmDetailActivity,
                                            "Berhasil dihapus dari favorite",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                            Status.ERROR -> {
                                showLoading(false)
                                Toast.makeText(
                                    this@FilmDetailActivity,
                                    "Terjadi kesalahan",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                })
            }
        } else {

            val id = intent.getStringExtra(EXTRA_ID)!!.toInt()
            Log.d("moovieId", id.toString())
            val factory = GetMoviesViewModelFactory.getInstance(this)
            val viewModel = ViewModelProvider(this, factory)[GetMoviesViewModel::class.java]
            viewModel.setSelectedMovie(id)
            showLoading(true)
            binding.apply {
                viewModel.movie.observe(this@FilmDetailActivity, {
                    if (it != null) {
                        when (it.status) {
                            Status.LOADING -> showLoading(true)
                            Status.SUCCESS -> {
                                Glide.with(this@FilmDetailActivity)
                                    .load("https://image.tmdb.org/t/p/original/" + it.data?.poster_path)
                                    .centerCrop()
                                    .into(imgPoster)

                                tvTitle.text = it.data?.original_title
                                tvPlaytime.text = it.data?.release_date
                                tvRating.text = it.data?.vote_average.toString() + "/10"
                                tvOverview.text = it.data?.overview
                                val favoriteStatus = it.data?.favorite
                                showLoading(false)

                                Log.d("movieData", it.data.toString())

                                btnFavorite.setOnClickListener {
                                    viewModel.setFavoriteMovie()
                                    if (favoriteStatus == false) {
                                        Toast.makeText(
                                            this@FilmDetailActivity,
                                            "Berhasil ditambahkan sebagai favorite",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Toast.makeText(
                                            this@FilmDetailActivity,
                                            "Berhasil dihapus dari favorite",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                            Status.ERROR -> {
                                showLoading(false)
                                Toast.makeText(
                                    this@FilmDetailActivity,
                                    "Terjadi kesalahan",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                })
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}