package com.example.newsapp.api

import com.example.newsapp.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WepServices {
    @GET("/v2/top-headlines/sources")
    fun getTabs(
        @Query("apiKey")key:String

    ):Call<Response>
}