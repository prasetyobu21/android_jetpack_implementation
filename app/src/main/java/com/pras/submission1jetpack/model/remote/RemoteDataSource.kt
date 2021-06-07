package com.pras.submission1jetpack.model.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pras.submission1jetpack.model.remote.response.*
import com.pras.submission1jetpack.utils.EspressoIdlingResource

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    val tvListResponse = MutableLiveData<List<TvListResponse>>()

    fun getTvList(): LiveData<ApiResponse<List<TvListResponse>>> {
        EspressoIdlingResource.increment()
        val resultTvList = MutableLiveData<ApiResponse<List<TvListResponse>>>()
        handler.postDelayed({
            resultTvList.value = ApiResponse.success(jsonHelper.loadTvList())
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                EspressoIdlingResource.decrement()
            }
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultTvList
    }

    fun getTvDetail(id: Int): LiveData<ApiResponse<TvListResponse>> {
        EspressoIdlingResource.increment()
        val resultTvDetail = MutableLiveData<ApiResponse<TvListResponse>>()
        handler.postDelayed(
            {
                resultTvDetail.value = ApiResponse.success(jsonHelper.loadTvDetail(id))
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            },
            SERVICE_LATENCY_IN_MILLIS
        )
        return resultTvDetail
    }

    fun getMovieList(): LiveData<ApiResponse<List<MovieListResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovieList = MutableLiveData<ApiResponse<List<MovieListResponse>>>()
        handler.postDelayed(
            {
                resultMovieList.value = ApiResponse.success(jsonHelper.loadMovieList())
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            },
            SERVICE_LATENCY_IN_MILLIS
        )
        return resultMovieList
    }

    fun getMovieDetail(id: Int): LiveData<ApiResponse<MovieListResponse>> {
        EspressoIdlingResource.increment()
        val resultMovieDetail = MutableLiveData<ApiResponse<MovieListResponse>>()
        handler.postDelayed(
            {
                resultMovieDetail.value = ApiResponse.success(jsonHelper.loadMovieDetail(id))
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            },
            SERVICE_LATENCY_IN_MILLIS
        )
        return resultMovieDetail
    }
}