package com.sinau.core.domain.usecase

import com.sinau.core.data.Resource
import com.sinau.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getPopularMovies(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun getCountFavoriteMovie(): Flow<Int>
    fun setFavoriteMovie(movie: Movie, newState: Boolean)
}