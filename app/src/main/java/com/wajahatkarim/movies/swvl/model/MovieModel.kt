package com.wajahatkarim.movies.swvl.model

data class MovieModel(
    val movieId: Long = 0,
    val title: String,
    val year: Int = 2020,
    val rating: Float = 0f,
    val cast: List<String> = emptyList(),
    val genres: List<String> = emptyList()
)