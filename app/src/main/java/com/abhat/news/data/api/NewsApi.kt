package com.abhat.news.data.api

import com.abhat.news.data.model.NewsApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Anirudh Uppunda on 8/3/18.
 */
interface NewsApi {

    @GET("top-headlines")
    fun getNews(@Query("country") country: String, @Query("pageSize") pageSize: String, @Query("apiKey") apiKey: String): Single<NewsApiResponse>

    @GET("top-headlines")
    fun getNewsFromSource(@Query("sources") sources: String, @Query("pageSize") pageSize: String, @Query("apiKey") apiKey: String): Single<NewsApiResponse>
}