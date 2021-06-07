package com.pras.submission1jetpack.model.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.pras.submission1jetpack.model.local.entity.MovieListEntity
import com.pras.submission1jetpack.model.local.entity.TvListEntity
import com.pras.submission1jetpack.model.local.room.AppDao
import com.pras.submission1jetpack.model.remote.response.MovieListResponse
import com.pras.submission1jetpack.model.remote.response.TvListResponse

class LocalDataSource private constructor(private val mAppDao: AppDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(appDao: AppDao): LocalDataSource = INSTANCE ?: LocalDataSource(appDao)
    }

    fun getMovieList(): DataSource.Factory<Int, MovieListEntity> = mAppDao.getMovieList()

    fun getTvList(): DataSource.Factory<Int, TvListEntity> = mAppDao.getTvList()

    fun getMovieDetail(movieId: Int): LiveData<MovieListEntity> = mAppDao.getMovieById(movieId)

    fun getTvDetail(tvId: Int): LiveData<TvListEntity> = mAppDao.getTvById(tvId)

    fun setMovieFavorite(movie: MovieListEntity, newState: Boolean) {
        movie.favorite = newState
        mAppDao.updateMovie(movie)
    }

    fun setTvFavorite(tv: TvListEntity, newState: Boolean) {
        tv.favorite = newState
        mAppDao.updateTv(tv)
    }

    fun getFavoriteMovie(): DataSource.Factory<Int, MovieListEntity> = mAppDao.getFavoriteMovie()

    fun getFavoriteTv(): DataSource.Factory<Int, TvListEntity> = mAppDao.getFavoriteTv()

    fun insertMovies(movie: List<MovieListEntity>) = mAppDao.insertMovie(movie)

    fun insertTv(tv: List<TvListEntity>) = mAppDao.insertTv(tv)

    fun insertMovieDetail(movie: MovieListEntity) = mAppDao.insertMovieDetail(movie)

    fun insertTvDetail(tv: TvListEntity) = mAppDao.insertTvDetail(tv)
}