package com.pras.submission1jetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.pras.submission1jetpack.model.DummyData
import com.pras.submission1jetpack.model.TvDetailEntity
import com.pras.submission1jetpack.model.local.entity.TvListEntity
import com.pras.submission1jetpack.model.repository.TvRepo
import org.junit.Test
import com.pras.submission1jetpack.vo.Resource

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetTvShowsViewModelTest {

    private lateinit var viewModel: GetTvShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvRepo: TvRepo

    @Mock
    private lateinit var detailObserver: Observer<Resource<TvListEntity>>

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvListEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvListEntity>

    @Before
    fun setUp() {
        viewModel = GetTvShowsViewModel(tvRepo)
        viewModel.setSelectedTv(1399)
    }

    @Test
    fun getTvList() {
        val dummyList = Resource.success(pagedList)
        `when`(dummyList.data?.size).thenReturn(5)
        val tv = MutableLiveData<Resource<PagedList<TvListEntity>>>()
        tv.value = dummyList

        `when`(tvRepo.getTvList()).thenReturn(tv)
        val tvEntities = viewModel.getTvList().value?.data
        verify(tvRepo).getTvList()
        assertNotNull(tvEntities)
        assertEquals(5, tvEntities?.size)

        viewModel.getTvList().observeForever(observer)
        verify(observer).onChanged(dummyList)
    }

    @Test
    fun getTvDetail() {
        val dummyTv = Resource.success(DummyData.generateTvDetail())
        val tv = MutableLiveData<Resource<TvListEntity>>()
        tv.value = dummyTv

        `when`(tvRepo.getTvDetail(1399)).thenReturn(tv)
        viewModel.tv.observeForever(detailObserver)
        verify(detailObserver).onChanged(dummyTv)
    }
}