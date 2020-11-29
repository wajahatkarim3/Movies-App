package com.wajahatkarim.movies.swvl.ui.movieslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wajahatkarim.movies.swvl.data.SwvlRepository
import com.wajahatkarim.movies.swvl.model.MovieModel
import com.wajahatkarim.movies.swvl.utils.readAsString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.InputStream
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(private val repository: SwvlRepository) : ViewModel() {

    private var _uiState = MutableLiveData<MoviesListUiState>()
    val uiStateLiveData: LiveData<MoviesListUiState> = _uiState

    private var _moviesList = MutableLiveData<List<MovieModel>>()
    val moviesList: LiveData<List<MovieModel>> = _moviesList

    fun init() {
        loadMovies()
    }

    fun loadMovies() {
        if (repository.areMoviesInDatabase()) {
            _uiState.value = LoadingState
            loadMoviesFromDatabase()
        } else {
            // Read movies from Assets file
            _uiState.value = ReadAssetsState
        }
    }

    fun loadMoviesFromDatabase() {
        viewModelScope.launch {
            repository.getAllMoviesFromDatabase().collect {
                _moviesList.postValue(it)
                _uiState.postValue(if (it.isEmpty()) EmptyState else ContentState)
            }
        }
    }

    fun readMoviesDataFromAssets(inputStream: InputStream) {
        _uiState.value = LoadingState
        viewModelScope.launch(Dispatchers.IO) {
            var moviesStr = inputStream.readAsString()
            if (moviesStr != null) {
                repository.saveAssetMoviesInDatabase(moviesStr)
                loadMovies()
            } else {
                _uiState.postValue(ErrorState("Couldn't read Assets"))
            }
        }
    }
}