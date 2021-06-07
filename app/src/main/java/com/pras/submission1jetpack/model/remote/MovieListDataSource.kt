package com.pras.submission1jetpack.model.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.pras.submission1jetpack.model.local.entity.MovieListEntity
import com.pras.submission1jetpack.vo.Resource

interface MovieListDataSource {
    fun getMovieList(): LiveData<Resource<PagedList<MovieListEntity>>>
    fun getMovieDetail(id: Int): LiveData<Resource<MovieListEntity>>
    fun setFavoriteMovie(movie: MovieListEntity, state: Boolean)
    fun getFavoriteMovie(): LiveData<PagedList<MovieListEntity>>
}