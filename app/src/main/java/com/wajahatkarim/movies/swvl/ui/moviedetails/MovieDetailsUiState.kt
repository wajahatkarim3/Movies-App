package com.wajahatkarim.movies.swvl.ui.moviedetails

sealed class MovieDetailsUiState

object LoadingState: MovieDetailsUiState()
object ContentState: MovieDetailsUiState()
class ErrorState(val message: String) : MovieDetailsUiState()