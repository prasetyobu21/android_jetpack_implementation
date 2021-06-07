package com.pras.submission1jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.pras.submission1jetpack.model.local.entity.MovieListEntity
import com.pras.submission1jetpack.model.repository.MovieRepo

class FavoriteMovieViewModel(private val repo: MovieRepo):ViewModel() {
    fun getFavorieMovie(): LiveData<PagedList<MovieListEntity>> = repo.getFavoriteMovie()
}