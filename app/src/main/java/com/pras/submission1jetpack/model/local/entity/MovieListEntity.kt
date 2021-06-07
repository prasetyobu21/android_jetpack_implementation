package com.pras.submission1jetpack.model.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movielistentity")
@Parcelize
data class MovieListEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var id: Int,

    @ColumnInfo(name = "title")
    var original_title: String,

    @ColumnInfo(name = "rating")
    var vote_average: Double,

    @ColumnInfo(name = "poster")
    var poster_path: String,

    @ColumnInfo(name = "releaseDate")
    var release_date: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
) : Parcelable