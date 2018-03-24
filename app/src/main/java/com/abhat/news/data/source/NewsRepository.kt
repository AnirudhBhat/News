package com.abhat.news.data.source

import com.abhat.news.data.api.NewsApi
import com.abhat.news.data.model.NewsApiResponse
import io.reactivex.Single

/**
 * Created by Anirudh Uppunda on 8/3/18.
 */
open class NewsRepository(private val newsApi: NewsApi): NewsDataSource {

    override fun getNews(country: String, pageSize: String, apiKey: String): Single<NewsApiResponse> {
        return newsApi.getNews(country, pageSize, apiKey)
    }

    override fun getNewsFromSource(source: String, pageSize: String, apiKey: String): Single<NewsApiResponse> {
        return newsApi.getNewsFromSource(source, pageSize, apiKey)
    }

}