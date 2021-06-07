package com.pras.submission1jetpack.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    var poster: Int = 0,
    var title: String,
    var genre: String,
    var rating: Double,
    var duration: String,
    var director: String,
    var overview: String
): Parcelable