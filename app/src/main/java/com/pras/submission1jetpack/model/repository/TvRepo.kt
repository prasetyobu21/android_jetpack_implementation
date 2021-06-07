package com.pras.submission1jetpack.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.pras.submission1jetpack.model.TvDetailEntity
import com.pras.submission1jetpack.model.local.LocalDataSource
import com.pras.submission1jetpack.model.local.entity.TvListEntity
import com.pras.submission1jetpack.model.remote.ApiResponse
import com.pras.submission1jetpack.model.remote.TvListDataSource
import com.pras.submission1jetpack.model.remote.RemoteDataSource
import com.pras.submission1jetpack.model.remote.response.TvDetailResponse
import com.pras.submission1jetpack.model.remote.response.TvListResponse
import com.pras.submission1jetpack.utils.AppExecutors
import com.pras.submission1jetpack.utils.NetworkBoundResource
import com.pras.submission1jetpack.vo.Resource

class TvRepo private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    TvListDataSource {
    companion object {
        @Volatile
        private var instance: TvRepo? = null

        fun getInstance(
            RemoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): TvRepo =
            instance ?: synchronized(this) {
                instance ?: TvRepo(
                    RemoteDataSource,
                    localDataSource,
                    appExecutors
                ).apply { instance = this }
            }
    }

    override fun getTvList(): LiveData<Resource<PagedList<TvListEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvListEntity>, List<TvListResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvListEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvList(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvListEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvListResponse>>> =
                remoteDataSource.getTvList()

            override fun saveCallResult(data: List<TvListResponse>) {
                val tvList = ArrayList<TvListEntity>()
                for (response in data) {
                    Log.d("tvlistdata", "Name = " + response.name)
                    val tvShow = TvListEntity(
                        response.id,
                        response.name,
                        response.vote_average,
                        response.poster_path,
                        response.first_air_date,
                        response.overview
                    )
                    tvList.add(tvShow)
                }
                localDataSource.insertTv(tvList)
            }

        }.asLiveData()
    }

    override fun getTvDetail(id: Int): LiveData<Resource<TvListEntity>> {
        return object : NetworkBoundResource<TvListEntity, TvListResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvListEntity> = localDataSource.getTvDetail(id)


            override fun shouldFetch(data: TvListEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<TvListResponse>> =
                remoteDataSource.getTvDetail(id)

            override fun saveCallResult(data: TvListResponse) {
                val tv = TvListEntity(
                    data.id,
                    data.name,
                    data.vote_average,
                    data.poster_path,
                    data.first_air_date,
                    data.overview
                )
                localDataSource.insertTvDetail(tv)
            }

        }.asLiveData()
    }

    override fun setFavoriteTv(tv: TvListEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setTvFavorite(tv, state) }
    }

    override fun getFavoriteTv(): LiveData<PagedList<TvListEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTv(), config).build()
    }
}