package com.pras.submission1jetpack.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pras.submission1jetpack.utils.Injection
import com.pras.submission1jetpack.model.repository.MovieRepo

class GetMoviesViewModelFactory private constructor(private val mMovieRepo: MovieRepo) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: GetMoviesViewModelFactory? = null

        fun getInstance(context: Context): GetMoviesViewModelFactory =
            instance ?: synchronized(this) {
                instance
                    ?: GetMoviesViewModelFactory(Injection.provideMovieRepository(context)).apply {
                        instance = this
                    }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(GetMoviesViewModel::class.java) -> {
                return GetMoviesViewModel(mMovieRepo) as T
            }
            else -> throw  Throwable("Unknown ViewModel class " + modelClass.name)
        }
    }
}