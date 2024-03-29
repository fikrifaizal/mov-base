package com.sinau.movbase.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sinau.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {

    val movies = movieUseCase.getPopularMovies().asLiveData()
}