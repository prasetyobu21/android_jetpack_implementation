package com.pras.submission1jetpack.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailEntity(
        val id: Int = 0,
        val original_title: String,
        val vote_average: Double,
        val poster_path: String,
        val release_date: String,
        val overview: String
): Parcelable
