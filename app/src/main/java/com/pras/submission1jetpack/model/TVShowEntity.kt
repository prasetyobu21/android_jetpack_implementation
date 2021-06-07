package com.pras.submission1jetpack.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TVShowEntity(
    var poster: Int,
    var title: String,
    var genre: String,
    var rating: Double,
    var seasons: Int,
    var creator: String,
    var overview: String
): Parcelable