package com.pras.submission1jetpack.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.pras.submission1jetpack.model.local.entity.TvListEntity
import com.pras.submission1jetpack.model.repository.TvRepo
import com.pras.submission1jetpack.vo.Resource

class GetTvShowsViewModel(private val repo: TvRepo) : ViewModel() {

    val tvId = MutableLiveData<Int>()

    fun getTvList(): LiveData<Resource<PagedList<TvListEntity>>> = repo.getTvList()
    fun getTvDetail(id: Int): LiveData<Resource<TvListEntity>> = repo.getTvDetail(id)

    fun setSelectedTv(tvId: Int){
        this.tvId.value = tvId
    }

    val tv: LiveData<Resource<TvListEntity>> = Transformations.switchMap(tvId) { mTvId ->
        Log.d("favStatus", "switchMap running")
        repo.getTvDetail(mTvId)
    }

    fun setFavoriteTv(){
        Log.d("favStatus", "setFavTv is running")
        Log.d("favStatus", "tvId: " + tvId.value.toString())
        val tvResource = tv.value
        Log.d("favStatus", "tvResource: " + tvResource?.data.toString())
        if (tvResource != null){
            val tvEntity = tvResource.data
            if (tvEntity != null){
                val newState = !tvEntity.favorite
                repo.setFavoriteTv(tvEntity, newState)
            }
        }
    }
}