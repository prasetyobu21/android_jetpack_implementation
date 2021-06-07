package com.pras.submission1jetpack.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.pras.submission1jetpack.model.local.LocalDataSource
import com.pras.submission1jetpack.model.local.entity.MovieListEntity
import com.pras.submission1jetpack.model.remote.ApiResponse
import com.pras.submission1jetpack.model.remote.MovieListDataSource
import com.pras.submission1jetpack.model.remote.RemoteDataSource
import com.pras.submission1jetpack.model.remote.response.MovieListResponse
import com.pras.submission1jetpack.utils.AppExecutors
import com.pras.submission1jetpack.utils.NetworkBoundResource
import com.pras.submission1jetpack.vo.Resource

class FakeMovieRepo(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieListDataSource {
    override fun getMovieList(): LiveData<Resource<PagedList<MovieListEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieListEntity>, List<MovieListResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieListEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovieList(), config).build()
            }


            override fun shouldFetch(data: PagedList<MovieListEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieListResponse>>> =
                remoteDataSource.getMovieList()

            override fun saveCallResult(data: List<MovieListResponse>) {
                val movieList = ArrayList<MovieListEntity>()
                for (response in data) {
                    Log.d("movielistdata", "Name = " + response.original_title)
                    val movie = MovieListEntity(
                        response.id,
                        response.original_title,
                        response.vote_average,
                        response.poster_path,
                        response.release_date,
                        response.overview
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(id: Int): LiveData<Resource<MovieListEntity>> {
        return object : NetworkBoundResource<MovieListEntity, MovieListResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieListEntity> =
                localDataSource.getMovieDetail(id)

            override fun shouldFetch(data: MovieListEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<MovieListResponse>> =
                remoteDataSource.getMovieDetail(id)

            override fun saveCallResult(data: MovieListResponse) {
                val movie = MovieListEntity(
                    data.id,
                    data.original_title,
                    data.vote_average,
                    data.poster_path,
                    data.release_date,
                    data.overview
                )
                localDataSource.insertMovieDetail(movie)
            }

        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: MovieListEntity, state: Boolean) {
        appExecutors.diskIO().execute{localDataSource.setMovieFavorite(movie, state)}
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieListEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }
}