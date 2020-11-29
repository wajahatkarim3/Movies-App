package com.wajahatkarim.movies.swvl.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wajahatkarim.movies.swvl.data.DataState
import com.wajahatkarim.movies.swvl.data.SwvlRepository
import com.wajahatkarim.movies.swvl.model.ImageModel
import com.wajahatkarim.movies.swvl.model.MovieModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(private val repository: SwvlRepository) : ViewModel() {

    private val _movieModel = MutableLiveData<MovieModel>()
    val movieModel: LiveData<MovieModel> = _movieModel

    private val _uiState = MutableLiveData<MovieDetailsUiState>()
    val uiState: LiveData<MovieDetailsUiState> = _uiState

    private val _photosList = MutableLiveData<List<ImageModel>>()
    val photosList: LiveData<List<ImageModel>> = _photosList

    fun init(movie: MovieModel) {
        _movieModel.value = movie
        loadFlickerImages(movie.title)
    }

    private fun loadFlickerImages(title: String) {
        viewModelScope.launch {
            repository.searchMovieImages(query = title).collect { dataState ->
                when(dataState) {
                    is DataState.Success -> {
                        _photosList.value = dataState.data
                        _uiState.value = ContentState
                    }
                    is DataState.Error -> {
                        _uiState.value = ErrorState(dataState.message)
                    }
                }
            }
        }
    }
}