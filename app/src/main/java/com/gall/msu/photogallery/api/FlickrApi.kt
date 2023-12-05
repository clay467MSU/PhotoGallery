package com.gall.msu.photogallery.api

import retrofit2.http.GET

private const val API_KEY = "8a9c8c2f518267c7c1ad2b96079c6688"

interface FlickrApi {
    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
            "&api_key=$API_KEY" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&extras=url_s"
    )
    suspend fun fetchPhotos(): FlickrResponse
}
