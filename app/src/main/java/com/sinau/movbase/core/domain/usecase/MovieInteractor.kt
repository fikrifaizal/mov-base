package com.sinau.movbase.core.domain.usecase

import com.sinau.movbase.core.data.MovieRepository
import com.sinau.movbase.core.data.Resource
import com.sinau.movbase.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: MovieRepository): MovieUseCase {

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> = movieRepository.getPopularMovies()

    override fun getFavoriteMovies(): Flow<List<Movie>> = movieRepository.getFavoriteMovies()

    override fun setFavoriteMovie(movie: Movie, newState: Boolean) = movieRepository.setFavoriteMovie(movie, newState)
}