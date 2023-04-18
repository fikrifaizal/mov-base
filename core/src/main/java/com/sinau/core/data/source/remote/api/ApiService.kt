package com.sinau.core.data.source.remote.api

import com.sinau.core.data.source.remote.response.PopularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query
import com.sinau.core.BuildConfig

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String = BuildConfig.IMDB_TOKEN
    ): PopularMovieResponse
}