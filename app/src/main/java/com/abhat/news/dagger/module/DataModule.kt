package com.abhat.news.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Anirudh Uppunda on 20/2/18.
 */
@Module
internal class DataModule(private val baseUrl: String) {

    companion object {
        private val CACHE_SIZE = 10 * 1024 * 1024 * 1L // 10 MB
    }

    @Provides
    @Singleton
    internal fun provideHttpCache(context: Context) = Cache(context.cacheDir, CACHE_SIZE)


    @Provides
    @Singleton
    internal fun provideOkHttpClient(cache: Cache) = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

}