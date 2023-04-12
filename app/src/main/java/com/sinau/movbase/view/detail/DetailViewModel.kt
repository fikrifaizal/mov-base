package com.sinau.movbase.view.detail

import androidx.lifecycle.ViewModel
import com.sinau.movbase.core.data.MovieRepository
import com.sinau.movbase.core.data.source.remote.response.MovieItemResponse
import com.sinau.movbase.core.domain.model.Movie
import com.sinau.movbase.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun setFavoriteMovie(movie: Movie, newState: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newState)
}