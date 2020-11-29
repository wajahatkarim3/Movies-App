package com.wajahatkarim.movies.swvl.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageModel(

    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("owner")
    @Expose
    var owner: String? = null,

    @SerializedName("secret")
    @Expose
    var secret: String? = null,

    @SerializedName("server")
    @Expose
    var server: String? = null,

    @SerializedName("farm")
    @Expose
    var farm: Int? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("ispublic")
    @Expose
    var ispublic: Int? = null,

    @SerializedName("isfriend")
    @Expose
    var isfriend: Int? = null,

    @SerializedName("isfamily")
    @Expose
    var isfamily: Int? = null
) {
    fun getImageUrl(): String {
        return "https://farm$farm.static.flickr.com/$server/${id}_$secret.jpg"
    }
}