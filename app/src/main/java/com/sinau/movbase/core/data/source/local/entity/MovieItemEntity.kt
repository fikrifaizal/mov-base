package com.sinau.movbase.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_movie")
data class MovieItemEntity (

    @PrimaryKey
    @ColumnInfo(name = "movieId")
    var movieId: Int,

    @ColumnInfo(name = "poster_path")
    var posterPath: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "release_date")
    var releaseDate: String,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
)