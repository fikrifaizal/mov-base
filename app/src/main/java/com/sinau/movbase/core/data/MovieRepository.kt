package com.sinau.movbase.core.data

import com.sinau.movbase.core.data.source.local.LocalDataSource
import com.sinau.movbase.core.data.source.remote.RemoteDataSource
import com.sinau.movbase.core.data.source.remote.api.ApiResponse
import com.sinau.movbase.core.data.source.remote.response.MovieItemResponse
import com.sinau.movbase.core.domain.model.Movie
import com.sinau.movbase.core.domain.repository.IMovieRepository
import com.sinau.movbase.core.utils.AppExecutors
import com.sinau.movbase.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieItemResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getPopularMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItemResponse>>> =
                remoteDataSource.getPopularMovies()

            override suspend fun saveCallResult(data: List<MovieItemResponse>) {
                val movies = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movies)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty() || Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == 1

        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, newState: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, newState)}
    }
}