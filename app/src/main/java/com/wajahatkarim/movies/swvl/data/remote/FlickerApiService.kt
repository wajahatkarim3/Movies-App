package com.wajahatkarim.movies.swvl.data.remote

import com.wajahatkarim.movies.swvl.data.remote.responses.ImageSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickerApiService {

    @GET("services/rest")
    suspend fun getImages(
        @Query("method") method: String = "flickr.photos.search",
        @Query("api_key") apiKey: String,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") callback: Int = 1,
        @Query("text") title: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<ImageSearchResponse>

    companion object {
        const val FLICKER_API_URL = "https://api.flickr.com/"
    }
}