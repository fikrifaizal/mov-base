package com.sinau.movbase.core.data.source.remote.api

import com.sinau.movbase.core.data.source.remote.response.MovieItemResponse
import com.sinau.movbase.core.data.source.remote.response.PopularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.sinau.movbase.BuildConfig as BC

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String = BC.IMDB_TOKEN
    ): PopularMovieResponse

    @GET("movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id: Int,
        @Query("api_key") key: String = BC.IMDB_TOKEN
    ): MovieItemResponse
}