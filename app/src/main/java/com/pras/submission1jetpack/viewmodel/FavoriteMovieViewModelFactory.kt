package com.pras.submission1jetpack.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pras.submission1jetpack.model.repository.MovieRepo
import com.pras.submission1jetpack.utils.Injection

class FavoriteMovieViewModelFactory private constructor(private val mMovieRepo: MovieRepo): ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var instance: FavoriteMovieViewModelFactory? = null

        fun getInstance(context: Context): FavoriteMovieViewModelFactory =
            instance?: synchronized(this){
                instance ?: FavoriteMovieViewModelFactory(Injection.provideMovieRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                return FavoriteMovieViewModel(mMovieRepo) as T
            }
            else -> throw Throwable("Unknown ViewModel class" + modelClass.name)
        }
    }
}