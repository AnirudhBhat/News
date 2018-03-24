package com.abhat.news.data.source

import com.abhat.news.data.model.NewsApiResponse
import io.reactivex.Single

/**
 * Created by Anirudh Uppunda on 8/3/18.
 */
interface NewsDataSource {
    fun getNews(country: String, pageSize: String, apiKey: String): Single<NewsApiResponse>

    fun getNewsFromSource(source: String, pageSize: String, apiKey: String): Single<NewsApiResponse>
}