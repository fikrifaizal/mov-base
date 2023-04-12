package com.sinau.movbase.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    var movieId: Int,
    var posterPath: String,
    var title: String,
    var overview: String,
    var releaseDate: String,
    var voteAverage: Double
) : Parcelable