package com.sinau.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sinau.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val favoriteMovies = movieUseCase.getFavoriteMovies().asLiveData()

    val countFavoriteMovie = movieUseCase.getCountFavoriteMovie().asLiveData()
}