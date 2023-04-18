package com.sinau.core.domain.repository

import com.sinau.core.data.Resource
import com.sinau.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getPopularMovies(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun getCountFavoriteMovie(): Flow<Int>

    fun setFavoriteMovie(movie: Movie, newState: Boolean)
}