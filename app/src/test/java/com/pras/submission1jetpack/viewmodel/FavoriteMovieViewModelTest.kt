package com.pras.submission1jetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.pras.submission1jetpack.model.local.entity.MovieListEntity
import com.pras.submission1jetpack.model.repository.MovieRepo
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest : TestCase() {

    private lateinit var viewModel: FavoriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepo: MovieRepo

    @Mock
    private lateinit var observer: Observer<PagedList<MovieListEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieListEntity>

    @Before
    fun SetUp(){
        viewModel = FavoriteMovieViewModel(movieRepo)
    }

    @Test
    fun testGetFavorieMovie() {
        val dummyList = pagedList
        `when`(dummyList.size).thenReturn(5)
        val favoriteMovies = MutableLiveData<PagedList<MovieListEntity>>()
        favoriteMovies.value = dummyList

        `when`(movieRepo.getFavoriteMovie()).thenReturn(favoriteMovies)
        val movieEntities = viewModel.getFavorieMovie().value
        verify<MovieRepo>(movieRepo).getFavoriteMovie()
        assertNotNull(movieEntities)
        assertEquals(5, movieEntities?.size)

        viewModel.getFavorieMovie().observeForever(observer)
        verify(observer).onChanged(dummyList)
    }
}