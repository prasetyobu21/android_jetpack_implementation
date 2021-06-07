package com.pras.submission1jetpack.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pras.submission1jetpack.utils.Injection
import com.pras.submission1jetpack.model.repository.TvRepo

class GetTvShowViewModelFactory private constructor(private val mTvRepo: TvRepo) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: GetTvShowViewModelFactory? = null

        fun getInstance(context: Context): GetTvShowViewModelFactory =
                instance ?: synchronized(this) {
                    instance
                            ?: GetTvShowViewModelFactory(Injection.provideTvRepository(context)).apply {
                                instance = this
                            }
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(GetTvShowsViewModel::class.java) -> {
                return GetTvShowsViewModel(mTvRepo) as T
            }
            else -> throw Throwable("Unknown ViewModel class " + modelClass.name)
        }
    }
}