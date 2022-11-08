package com.example.splashphoto.data.sources.remote

import com.example.splashphoto.data.sources.remote.model.PhotoRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApiService {

    @GET("v2/list")
    fun getPhotoList(
        @Query("page") page:Int ,
        @Query("limit") limit:Int,
        @Query("client_id") clientID: String
    ):List<PhotoRemote>
}