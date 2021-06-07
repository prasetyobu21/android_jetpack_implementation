package com.pras.submission1jetpack.model.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.pras.submission1jetpack.model.local.entity.MovieListEntity
import com.pras.submission1jetpack.model.local.entity.TvListEntity
import com.pras.submission1jetpack.model.remote.response.MovieListResponse
import com.pras.submission1jetpack.model.remote.response.TvListResponse

@Dao
interface AppDao {
    @Query("SELECT * FROM movielistentity")
    fun getMovieList(): DataSource.Factory<Int, MovieListEntity>

    @Query("SELECT * FROM tvlistentity")
    fun getTvList(): DataSource.Factory<Int, TvListEntity>

    @Query("SELECT * FROM movielistentity where favorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieListEntity>

    @Query("SELECT * FROM tvlistentity where favorite = 1")
    fun getFavoriteTv(): DataSource.Factory<Int, TvListEntity>

    @Transaction
    @Query("SELECT * FROM movielistentity WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): LiveData<MovieListEntity>

    @Query("SELECT * FROM tvlistentity WHERE tvId = :tvId")
    fun getTvById(tvId: Int): LiveData<TvListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieListEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tv: List<TvListEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movie: MovieListEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvDetail(tv: TvListEntity)

    @Update
    fun updateMovie(movie: MovieListEntity)

    @Update
    fun updateTv(tv: TvListEntity)
}