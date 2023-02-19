package com.example.newsapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object{
        val apiKay ="5f6dc894ee974449b30e38823dcfb9b0"
        private var retrofit :Retrofit?=null
        private fun getInstance() :Retrofit{
            if(retrofit==null){
                retrofit=Retrofit.Builder()
                    .baseUrl("https://newsapi.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit!!
        }

        fun getApi():WepServices{
            return getInstance().create(WepServices::class.java)
        }
    }
}