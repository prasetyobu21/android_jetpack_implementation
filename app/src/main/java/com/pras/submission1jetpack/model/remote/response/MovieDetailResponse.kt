package com.pras.submission1jetpack.model.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailResponse(
        val id: Int = 0,
        val original_title: String,
        val vote_average: Double,
        val poster_path: String,
        val release_date: String,
        val overview: String
): Parcelable