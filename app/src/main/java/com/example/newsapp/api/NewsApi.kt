package com.example.newsapp.api

import com.example.newsapp.models.NewsResponse
import com.example.newsapp.utils.Constants.Companion.API_Key
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country")
        countryCode:String="us",
        @Query("page")
        pageNumber: Int=1,
        @Query("apikey")
        api: String=API_Key
    ):Response<NewsResponse>


    @GET("")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int=1,
        @Query("apiKey")
        apikey:String= API_Key
    ):Response<NewsResponse>


}