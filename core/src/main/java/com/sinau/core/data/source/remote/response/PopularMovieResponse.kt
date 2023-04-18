package com.sinau.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(

    @field:SerializedName("results")
	val results: List<MovieItemResponse>
)
