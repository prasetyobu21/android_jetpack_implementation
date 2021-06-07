package com.pras.submission1jetpack.model.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.pras.submission1jetpack.model.DummyData
import com.pras.submission1jetpack.model.local.LocalDataSource
import com.pras.submission1jetpack.model.local.entity.TvListEntity
import com.pras.submission1jetpack.model.remote.RemoteDataSource
import com.pras.submission1jetpack.utils.AppExecutors
import com.pras.submission1jetpack.utils.PagedListUtil
import com.pras.submission1jetpack.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.ArgumentMatcher
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.verify

class TvRepoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val tvRepo = FakeTvRepo(remote, local, appExecutors)

    private val tvListResponse = DummyData.generateTvListResponse()
    private val tvDetailResponse = DummyData.generateTvDetailResponse()
    private val tvId = tvListResponse[0].id

    @Test
    fun getTvList() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvListEntity>
        `when`(local.getTvList()).thenReturn(dataSourceFactory)
        tvRepo.getTvList()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateTvList()))
        verify(local).getTvList()
        assertNotNull(tvEntities.data)
        assertEquals(tvListResponse.size.toLong(), tvEntities.data?.size?.toLong())
//        doAnswer { invocation ->
//            (invocation.arguments[0] as RemoteDataSource.loadTvListCallback)
//                .onTvListRecieved(tvListResponse)
//            null
//        }.`when`(remote).getTvList(any())
//
//        val tvEntity = LiveDataTestUtil.getValue(tvRepo.getTvList())
//        verify(remote).getTvList(any())
//        assertNotNull(tvEntity)
//        assertEquals(tvListResponse.size.toLong(), tvEntity.size.toLong())
    }

    @Test
    fun getTvDetail() {
        val dummyEntity = MutableLiveData<TvListEntity>()
        dummyEntity.value = DummyData.generateTvDetail()
        `when`(local.getTvDetail(tvId)).thenReturn(dummyEntity)

        val tvEntityDetail = LiveDataTestUtil.getValue(tvRepo.getTvDetail(tvId))
        verify(local).getTvDetail(tvId)
        assertNotNull(tvEntityDetail)
        assertNotNull(tvEntityDetail.data?.id)
        assertNotNull(tvEntityDetail.data?.name)
        assertEquals(tvId, tvEntityDetail.data?.id)
//        doAnswer { invocation ->
//            (invocation.arguments[1] as RemoteDataSource.loadTvDetailCallback)
//                .onTvDetailRecieved(tvDetailResponse)
//            null
//        }.`when`(remote).getTvDetail(ArgumentMatchers.eq(tvId), any())
//        val tvDetailEntity = LiveDataTestUtil.getValue(tvRepo.getTvDetail(tvId))
//        verify(remote).getTvDetail(ArgumentMatchers.eq(tvId), any())
//        assertNotNull(tvDetailEntity)
    }

    @Test
    fun getFavoriteTv(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvListEntity>
        `when`(local.getFavoriteTv()).thenReturn(dataSourceFactory)
        tvRepo.getFavoriteTv()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateTvList()))
        verify(local).getFavoriteTv()
        assertNotNull(tvEntities.data)
        assertEquals(tvListResponse.size.toLong(), tvEntities.data?.size?.toLong())
    }
}