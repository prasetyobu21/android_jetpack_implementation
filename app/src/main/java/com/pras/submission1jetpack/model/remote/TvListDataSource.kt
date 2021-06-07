package com.pras.submission1jetpack.model.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.pras.submission1jetpack.model.local.entity.TvListEntity
import com.pras.submission1jetpack.vo.Resource

interface TvListDataSource {
    fun getTvList(): LiveData<Resource<PagedList<TvListEntity>>>
    fun getTvDetail(id: Int): LiveData<Resource<TvListEntity>>
    fun setFavoriteTv(tv: TvListEntity, state: Boolean)
    fun getFavoriteTv(): LiveData<PagedList<TvListEntity>>
}