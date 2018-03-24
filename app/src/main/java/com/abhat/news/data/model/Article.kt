package com.abhat.news.data.model

/**
 * Created by Anirudh Uppunda on 8/3/18.
 */
data class Article(val source: Source,
                   val author: String?,
                   val title: String,
                   val description: String,
                   val url: String,
                   val urlToImage: String,
                   val publishedAt: String)