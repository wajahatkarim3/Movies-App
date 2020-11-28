package com.wajahatkarim.movies.swvl.ui.movieslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wajahatkarim.movies.swvl.data.SwvlRepository
import com.wajahatkarim.movies.swvl.utils.readAsString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(private val repository: SwvlRepository) : ViewModel() {

    private var _uiState = MutableLiveData<MoviesListUiState>()
    val uiStateLiveData: LiveData<MoviesListUiState> = _uiState

    fun init() {
        loadMovies()
    }

    fun loadMovies() {
        if (repository.areMoviesInDatabase()) {

        } else {
            // Read movies from Assets file
            _uiState.value = ReadAssetsState
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