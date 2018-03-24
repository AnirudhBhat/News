package com.abhat.news.data.model

/**
 * Created by Anirudh Uppunda on 8/3/18.
 */
data class NewsApiResponse(val status: String,
                           val totalResults: Int,
                           val articles: List<Article>)