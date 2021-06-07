package com.pras.submission1jetpack.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.pras.submission1jetpack.model.local.entity.MovieListEntity
import com.pras.submission1jetpack.model.repository.MovieRepo
import com.pras.submission1jetpack.vo.Resource

class GetMoviesViewModel(private val repo: MovieRepo) : ViewModel() {

    val movieId = MutableLiveData<Int>()

    fun getMovieList(): LiveData<Resource<PagedList<MovieListEntity>>> = repo.getMovieList()
    fun getMovieDetail(id: Int): LiveData<Resource<MovieListEntity>> {
        Log.d("favStatus", "movieIddddd: " + movieId.value.toString())
        val moviedetail = repo.getMovieDetail(id)
        return moviedetail
    }
//    repo.getMovieDetail(id)

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    val movie: LiveData<Resource<MovieListEntity>> =
        Transformations.switchMap(movieId) {
            Log.d("favStatus", "switchMap is running")
            repo.getMovieDetail(it)
        }

    fun setFavoriteMovie() {
        Log.d("favStatus", "setFavMovie is running")
        Log.d("favStatus", "movieId: " + movieId.value.toString())
        val movieResource = movie.value
        Log.d("favStatus", "MovieReseouce: " + movieResource?.data.toString())
        if (movieResource != null) {
            val movieEntity = movieResource.data
            Log.d("favStatus", movieEntity?.favorite.toString())
            if (movieEntity != null) {
                val newState = !movieEntity.favorite
                repo.setFavoriteMovie(movieEntity, newState)
                Log.d("favStatus", movieEntity.favorite.toString())
            }
        }
    }
}