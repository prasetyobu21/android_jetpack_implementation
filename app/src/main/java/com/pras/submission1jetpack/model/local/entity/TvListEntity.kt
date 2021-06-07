package com.pras.submission1jetpack.model.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tvlistentity")
@Parcelize
data class TvListEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvId")
    var id: Int,

    @ColumnInfo(name = "title")
    var name: String,

    @ColumnInfo(name = "rating")
    var vote_average: Double,

    @ColumnInfo(name = "poster")
    var poster_path: String,

    @ColumnInfo(name = "firstAirDate")
    var first_air_date: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
) : Parcelable