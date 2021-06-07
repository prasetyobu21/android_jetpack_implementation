package com.pras.submission1jetpack.model.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvListResponse(
        val id: Int,
        val name: String,
        val vote_average: Double,
        val poster_path: String,
        var first_air_date: String,
        var overview: String
): Parcelable