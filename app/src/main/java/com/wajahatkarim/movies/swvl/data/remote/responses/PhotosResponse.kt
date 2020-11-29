package com.wajahatkarim.movies.swvl.data.remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.wajahatkarim.movies.swvl.model.ImageModel

data class PhotosResponse(
    @SerializedName("photo")
    @Expose
    var photos: List<ImageModel>? = null
)