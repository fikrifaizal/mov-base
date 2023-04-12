package com.sinau.movbase.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sinau.movbase.core.data.MovieRepository
import com.sinau.movbase.core.data.source.remote.response.PopularMovieResponse
import com.sinau.movbase.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {

    val movies = movieUseCase.getPopularMovies().asLiveData()
}