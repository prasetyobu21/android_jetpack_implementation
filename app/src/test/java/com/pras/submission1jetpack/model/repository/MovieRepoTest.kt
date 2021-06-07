package com.pras.submission1jetpack.model.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.pras.submission1jetpack.model.DummyData
import com.pras.submission1jetpack.model.local.LocalDataSource
import com.pras.submission1jetpack.model.local.entity.MovieListEntity
import com.pras.submission1jetpack.model.remote.RemoteDataSource
import com.pras.submission1jetpack.utils.AppExecutors
import com.pras.submission1jetpack.utils.PagedListUtil
import com.pras.submission1jetpack.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import javax.sql.DataSource

class MovieRepoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieRepo = FakeMovieRepo(remote, local, appExecutors)

    private val movieListResponse = DummyData.generateMovieResponse()
    private val movieDetailResponse = DummyData.generateMovieDetailResponse()
    private val movieId = movieListResponse[0].id

    @Test
    fun getMovieList() {
        val dataSourceFactory = mock(androidx.paging.DataSource.Factory::class.java) as androidx.paging.DataSource.Factory<Int, MovieListEntity>
        `when`(local.getMovieList()).thenReturn(dataSourceFactory)
        movieRepo.getMovieList()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateMovieList()))
        verify(local).getMovieList()
        assertNotNull(movieEntities.data)
        assertEquals(movieListResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyEntity = MutableLiveData<MovieListEntity>()
        dummyEntity.value = DummyData.generateMovieDetail()
        `when`(local.getMovieDetail(movieId)).thenReturn(dummyEntity)

        val movieEntityDetail = LiveDataTestUtil.getValue(movieRepo.getMovieDetail(movieId))
        verify(local).getMovieDetail(movieId)
        assertNotNull(movieEntityDetail)
        assertNotNull(movieEntityDetail.data?.id)
        assertNotNull(movieEntityDetail.data?.original_title)
        assertEquals(movieId, movieEntityDetail.data?.id)
    }

    @Test
    fun getFavoriteMovie(){
        val dataSourceFactory = mock(androidx.paging.DataSource.Factory::class.java) as androidx.paging.DataSource.Factory<Int, MovieListEntity>
        `when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        movieRepo.getFavoriteMovie()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateMovieList()))
        verify(local).getFavoriteMovie()
        assertNotNull(movieEntities.data)
        assertEquals(movieListResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }
}