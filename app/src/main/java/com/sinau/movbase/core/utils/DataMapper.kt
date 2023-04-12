package com.sinau.movbase.core.utils

import com.sinau.movbase.core.data.source.local.entity.MovieItemEntity
import com.sinau.movbase.core.data.source.remote.response.MovieItemResponse
import com.sinau.movbase.core.domain.model.Movie

object DataMapper {

    fun mapResponsesToEntities(input: List<MovieItemResponse>): List<MovieItemEntity> {
        val movieList = ArrayList<MovieItemEntity>()
        input.map {
            val movie = MovieItemEntity(
                movieId = it.id,
                posterPath = it.posterPath,
                title = it.originalTitle,
                overview = it.overview,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieItemEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                posterPath = it.posterPath,
                title = it.title,
                overview = it.overview,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieItemEntity(
        movieId = input.movieId,
        posterPath = input.posterPath,
        title = input.title,
        overview = input.overview,
        releaseDate = input.releaseDate,
        voteAverage = input.voteAverage
    )
}