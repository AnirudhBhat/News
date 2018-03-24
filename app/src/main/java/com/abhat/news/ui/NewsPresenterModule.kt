package com.abhat.news.ui

import dagger.Module
import dagger.Provides

/**
 * Created by Anirudh Uppunda on 8/3/18.
 */
@Module
class NewsPresenterModule(private var view: NewsContract.View) {

    @Provides
    fun providesNewsView() = view
}