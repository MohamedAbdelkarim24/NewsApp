package com.route.newsapp.api

import com.route.newsapp.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")
    fun getResorces(@Query("apiKey") apiKey:String):Call<SourcesResponse>
}