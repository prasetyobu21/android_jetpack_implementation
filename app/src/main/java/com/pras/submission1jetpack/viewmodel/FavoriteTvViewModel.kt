package com.pras.submission1jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.pras.submission1jetpack.model.local.entity.TvListEntity
import com.pras.submission1jetpack.model.repository.TvRepo

class FavoriteTvViewModel(private val repo: TvRepo): ViewModel() {
    fun getFavoriteTv(): LiveData<PagedList<TvListEntity>> = repo.getFavoriteTv()
}