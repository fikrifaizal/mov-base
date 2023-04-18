package com.sinau.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieItemResponse(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("original_title")
	val originalTitle: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double
)
