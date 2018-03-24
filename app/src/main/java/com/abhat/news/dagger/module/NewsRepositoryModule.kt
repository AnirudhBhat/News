package com.abhat.news.dagger.module

import com.abhat.news.data.api.NewsApi
import com.abhat.news.data.source.NewsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Anirudh Uppunda on 8/3/18.
 */
@Module
class NewsRepositoryModule {

    @Provides
    fun getNewsApi(retrofit: Retrofit) = retrofit.create(NewsApi::class.java)

    @Provides
    fun provideNewsRepository(newsApi: NewsApi) = NewsRepository(newsApi)
}