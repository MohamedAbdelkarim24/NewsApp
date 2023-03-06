package com.route.newsapp.api

import com.route.newsapp.api.model.newsResponse.NewsResponse
import com.route.newsapp.api.model.sourcesResponse.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")
    fun getResorces(@Query("apiKey") apiKey:String):Call<SourcesResponse>
    @GET("v2/everything")
    fun getNews(@Query("apiKey") apiKey:String,
                @Query("sources") sources:String):Call<NewsResponse>
}