package com.pras.submission1jetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.lifecycle.Observer
import com.pras.submission1jetpack.model.local.entity.TvListEntity
import com.pras.submission1jetpack.model.repository.TvRepo
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvViewModelTest : TestCase() {

    private lateinit var viewModel: FavoriteTvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvRepo: TvRepo

    @Mock
    private lateinit var observer: Observer<PagedList<TvListEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvListEntity>

    @Before
    fun SetUp(){
        viewModel = FavoriteTvViewModel(tvRepo)
    }

    @Test
    fun testGetFavoriteTv() {
        val dummyList = pagedList
        `when`(dummyList.size).thenReturn(5)
        val favoriteTv = MutableLiveData<PagedList<TvListEntity>>()
        favoriteTv.value = dummyList

        `when`(tvRepo.getFavoriteTv()).thenReturn(favoriteTv)
        val tvEntities = viewModel.getFavoriteTv().value
        verify<TvRepo>(tvRepo).getFavoriteTv()
        assertNotNull(tvEntities)
        assertEquals(5, tvEntities?.size)

        viewModel.getFavoriteTv().observeForever(observer)
        verify(observer).onChanged(dummyList)
    }
}