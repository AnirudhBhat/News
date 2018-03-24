package com.abhat.news.dagger.component

import com.abhat.bytemarkassignment.dagger.scope.CustomScoped
import com.abhat.news.dagger.module.NewsRepositoryModule
import com.abhat.news.data.source.NewsRepository
import com.abhat.news.ui.NewsPresenterComponent
import com.abhat.news.ui.NewsPresenterModule
import dagger.Subcomponent

/**
 * Created by Anirudh Uppunda on 8/3/18.
 */
@CustomScoped
@Subcomponent(
        modules = [NewsRepositoryModule::class])
interface NewsRepositoryComponent {
    fun NewsRepositoryComponent(): NewsRepository

    fun plus(newsPresenterModule: NewsPresenterModule): NewsPresenterComponent
}