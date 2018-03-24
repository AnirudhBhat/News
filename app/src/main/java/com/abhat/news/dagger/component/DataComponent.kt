package com.abhat.news.dagger.component

import com.abhat.news.dagger.module.AppModule
import com.abhat.news.dagger.module.DataModule
import com.abhat.news.dagger.module.NewsRepositoryModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Anirudh Uppunda on 20/2/18.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, DataModule::class))
interface DataComponent {
    fun plus(newsRepositoryModule: NewsRepositoryModule): NewsRepositoryComponent
}