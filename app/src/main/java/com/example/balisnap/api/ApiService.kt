package com.example.balisnap.api


import com.example.balisnap.response.DestinationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("destinations/nearby")
    fun getDestination(
        @Query("latitude") latitude:Double,
        @Query("longitude") longitude:Double,
        @Query("radius") radius:Int
    ) : Call<DestinationResponse>

    @GET("destinations/nearby")
    fun getDetail(
        @Query("image") image:String,
        @Query("name") name:String,
        @Query("description") description:String
    ) : Call<DestinationResponse>
}