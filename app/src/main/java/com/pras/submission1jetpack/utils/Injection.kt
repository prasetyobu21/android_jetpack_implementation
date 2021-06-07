package com.pras.submission1jetpack.utils

import android.content.Context
import com.pras.submission1jetpack.model.local.LocalDataSource
import com.pras.submission1jetpack.model.local.room.AppDatabase
import com.pras.submission1jetpack.model.remote.JsonHelper
import com.pras.submission1jetpack.model.remote.RemoteDataSource
import com.pras.submission1jetpack.model.repository.MovieRepo
import com.pras.submission1jetpack.model.repository.TvRepo
import com.pras.submission1jetpack.viewmodel.FavoriteMovieViewModel

object Injection {
    fun provideTvRepository(context: Context): TvRepo{
        val database = AppDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.appDao())
        val appExecutors = AppExecutors()
        return TvRepo.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMovieRepository(context: Context): MovieRepo{
        val database = AppDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.appDao())
        val appExecutors = AppExecutors()
        return MovieRepo.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}