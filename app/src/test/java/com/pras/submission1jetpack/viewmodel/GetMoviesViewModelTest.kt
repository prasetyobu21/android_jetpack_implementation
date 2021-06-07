package com.pras.submission1jetpack.viewmodel

import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.pras.submission1jetpack.model.DummyData
import com.pras.submission1jetpack.model.MovieDetailEntity
import com.pras.submission1jetpack.model.local.entity.MovieListEntity
import com.pras.submission1jetpack.model.repository.MovieRepo
import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.pras.submission1jetpack.vo.Resource
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMoviesViewModelTest {

    private lateinit var viewModel: GetMoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepo: MovieRepo

    @Mock
    private lateinit var detailObserver: Observer<Resource<MovieListEntity>>

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieListEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieListEntity>

    @Before
    fun SetUp() {
        viewModel = GetMoviesViewModel(movieRepo)
        viewModel.setSelectedMovie(399566)
    }

    @Test
    fun getMovieList() {
        val dummyList = Resource.success(pagedList)
        `when`(dummyList.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MovieListEntity>>>()
        movies.value = dummyList

        `when`(movieRepo.getMovieList()).thenReturn(movies)
        val movieEntities = viewModel.getMovieList().value?.data
        verify(movieRepo).getMovieList()
        assertNotNull(movieEntities)
        assertEquals(5, movieEntities?.size)

        viewModel.getMovieList().observeForever(observer)
        verify(observer).onChanged(dummyList)
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = Resource.success(DummyData.generateMovieDetail())
        val movie = MutableLiveData<Resource<MovieListEntity>>()
        movie.value = dummyMovie

        `when`(movieRepo.getMovieDetail(399566)).thenReturn(movie)
        viewModel.movie.observeForever(detailObserver)
        verify(detailObserver).onChanged(dummyMovie)
    }
}