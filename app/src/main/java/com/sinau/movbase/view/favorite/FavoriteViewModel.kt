package com.sinau.movbase.view.favorite

import androidx.lifecycle.ViewModel
import com.sinau.movbase.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {

    val favoriteMovies = movieUseCase.getFavoriteMovies()
}