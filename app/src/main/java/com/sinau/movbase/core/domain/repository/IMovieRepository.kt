package com.sinau.movbase.core.domain.repository

import com.sinau.movbase.core.data.Resource
import com.sinau.movbase.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getPopularMovies(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, newState: Boolean)
}