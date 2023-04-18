package com.sinau.core.data.source.local.room

import androidx.room.*
import com.sinau.core.data.source.local.entity.MovieItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM popular_movie")
    fun getPopularMovies(): Flow<List<MovieItemEntity>>

    @Query("SELECT * FROM popular_movie WHERE is_favorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieItemEntity>>

    @Query("SELECT COUNT(movie_id) FROM popular_movie WHERE is_favorite = 1")
    fun getCountFavoriteMovie(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieItemEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieItemEntity)
}