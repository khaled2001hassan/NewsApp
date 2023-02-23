package com.example.newsapp.api

import com.example.newsapp.model.Response
import com.example.newsapp2.model.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WepServices {
    @GET("/v2/top-headlines/sources")
    fun getTabs(
        @Query("apiKey")key:String
    ):Call<Response>
    @GET("/v2/everything")
    fun getArtical(
        @Query("apiKey")key:String,
        @Query("sources")tab:String
    ):Call<ArticleResponse>
}