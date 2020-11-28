package com.wajahatkarim.movies.swvl.data.local.assets

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.wajahatkarim.movies.swvl.model.MovieModel

class AssetMoviesResponse {

    @SerializedName("movies")
    @Expose
    var movies: List<MovieModel>? = null
}