package com.sinau.core.data.source.local

import com.sinau.core.data.source.local.entity.MovieItemEntity
import com.sinau.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getPopularMovies(): Flow<List<MovieItemEntity>> = movieDao.getPopularMovies()

    fun getFavoriteMovies(): Flow<List<MovieItemEntity>> = movieDao.getFavoriteMovies()

    fun getCountFavoriteMovie(): Flow<Int> = movieDao.getCountFavoriteMovie()

    suspend fun insertMovie(movies: List<MovieItemEntity>) = movieDao.insertMovie(movies)

    fun setFavoriteMovie(movie: MovieItemEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}