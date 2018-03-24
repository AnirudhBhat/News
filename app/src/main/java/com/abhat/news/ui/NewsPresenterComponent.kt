package com.abhat.news.ui

import com.abhat.bytemarkassignment.dagger.scope.FragmentScoped
import dagger.Subcomponent

/**
 * Created by Anirudh Uppunda on 8/3/18.
 */
@FragmentScoped
@Subcomponent(
        modules = [NewsPresenterModule::class]
)
interface NewsPresenterComponent {
    fun inject(newsFragment: TopHeadlinesFragment)
}