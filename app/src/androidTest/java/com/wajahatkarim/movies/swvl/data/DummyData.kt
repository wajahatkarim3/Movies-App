package com.wajahatkarim.movies.swvl.data

import com.wajahatkarim.movies.swvl.model.MovieModel

object DummyData {

    fun getMoviesList() = listOf(
        MovieModel(
            movieId = 1,
            title = "500 Days of summer",
            year = 2009,
            cast = listOf("Joseph Gordon-Levitt","Zooey Deschanel"),
            genres = listOf("Romance","Comedy"),
            rating = 1
        ),
        MovieModel(
            movieId = 2,
            title = "12 Rounds",
            year = 2009,
            cast = listOf("John Cena",
                "Ashley Scott",
                "Steve Harris",
                "Aidan Gillen",
                "Brian J. White",
                "Taylor Cole"
            ),
            genres = listOf("Action"),
            rating = 1
        ),
        MovieModel(
            movieId = 3,
            title = "17 Again",
            year = 2009,
            cast = listOf("Zac Efron",
                "Leslie Mann",
                "Thomas Lennon",
                "Matthew Perry",
                "Melora Hardin",
                "Michelle Trachtenberg",
                "Sterling Knight"
            ),
            genres = listOf("Comedy", "Teen"),
            rating = 1
        )
    )

    fun getSingleMovie() = MovieModel(
        movieId = 3,
        title = "17 Again",
        year = 2009,
        cast = listOf("Zac Efron",
            "Leslie Mann",
            "Thomas Lennon",
            "Matthew Perry",
            "Melora Hardin",
            "Michelle Trachtenberg",
            "Sterling Knight"
        ),
        genres = listOf("Comedy", "Teen"),
        rating = 1
    )

    fun getAssetsMoviesStr(): String {
        return """
            {
  "movies": [
    {
      "title": "(500) Days of Summer",
      "year": 2009,
      "cast": [
        "Joseph Gordon-Levitt",
        "Zooey Deschanel"
      ],
      "genres": [
        "Romance",
        "Comedy"
      ],
      "rating": 1
    },
    {
      "title": "12 Rounds",
      "year": 2009,
      "cast": [
        "John Cena",
        "Ashley Scott",
        "Steve Harris",
        "Aidan Gillen",
        "Brian J. White",
        "Taylor Cole"
      ],
      "genres": [
        "Action"
      ],
      "rating": 1
    },
    {
      "title": "17 Again",
      "year": 2009,
      "cast": [
        "Zac Efron",
        "Leslie Mann",
        "Thomas Lennon",
        "Matthew Perry",
        "Melora Hardin",
        "Michelle Trachtenberg",
        "Sterling Knight"
      ],
      "genres": [
        "Comedy",
        "Teen"
      ],
      "rating": 1
    }]}
        """.trimIndent()
    }
}