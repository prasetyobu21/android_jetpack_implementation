package com.pras.submission1jetpack.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pras.submission1jetpack.model.repository.TvRepo
import com.pras.submission1jetpack.utils.Injection

class FavoriteTvViewModelFactory private constructor(private val mTvRepo: TvRepo): ViewModelProvider.NewInstanceFactory(){
    companion object {
        @Volatile
        private var instance: FavoriteTvViewModelFactory? = null

        fun getInstance(context: Context): FavoriteTvViewModelFactory =
            instance ?: synchronized(this) {
                instance
                    ?: FavoriteTvViewModelFactory(Injection.provideTvRepository(context)).apply {
                        instance = this
                    }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(FavoriteTvViewModel::class.java) -> {
                return FavoriteTvViewModel(mTvRepo) as T
            }
            else -> throw  Throwable("Unknown ViewModel class " + modelClass.name)
        }
    }
}