package com.wajahatkarim.movies.swvl.util

import com.wajahatkarim.movies.swvl.data.DataState
import com.wajahatkarim.movies.swvl.model.ImageModel
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
    
    fun getPhotosList() = DataState.success(listOf(
        ImageModel(
            id = "50653943763",
            owner = "61550823@N02",
            secret = "a165a9b7f5",
            server = "65535",
            farm = 66,
            title = "U.S.-Dakota Conflict Historic Marker Traverse des Sioux ~ St. Paul, Minnesota",
            ispublic = 1,
            isfriend = 0,
            isfamily = 0
        ),
        ImageModel(
            id = "50649243031",
            owner = "79597081@N07",
            secret = "067421a27f",
            server = "65535",
            farm = 66,
            title = "Village of Fairlie, Ayshire, Scotland",
            ispublic = 1,
            isfriend = 0,
            isfamily = 0
        ),
        ImageModel(
            id = "50642944001",
            owner = "34457823@N04",
            secret = "1c3671a0d4",
            server = "65535",
            farm = 66,
            title = "Butte, Montana",
            ispublic = 1,
            isfriend = 0,
            isfamily = 0
        )
    ))
}