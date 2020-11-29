package com.wajahatkarim.movies.swvl.data.remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.wajahatkarim.movies.swvl.model.ImageModel

data class ImageSearchResponse (

    @SerializedName("page")
    @Expose
    var page: Int? = null,

    @SerializedName("photos")
    @Expose
    var photos: PhotosResponse? = null
)