package com.sinau.core.domain.usecase

import com.sinau.core.data.MovieRepository
import com.sinau.core.data.Resource
import com.sinau.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: MovieRepository): MovieUseCase {

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> = movieRepository.getPopularMovies()

    override fun getFavoriteMovies(): Flow<List<Movie>> = movieRepository.getFavoriteMovies()

    override fun getCountFavoriteMovie(): Flow<Int> = movieRepository.getCountFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, newState: Boolean) = movieRepository.setFavoriteMovie(movie, newState)
}