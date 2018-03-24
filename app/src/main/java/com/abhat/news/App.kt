package com.abhat.news

import android.app.Application
import android.content.Context
import com.abhat.news.dagger.component.DaggerDataComponent
import com.abhat.news.dagger.component.DataComponent
import com.abhat.news.dagger.component.NewsRepositoryComponent
import com.abhat.news.dagger.module.AppModule
import com.abhat.news.dagger.module.DataModule
import com.abhat.news.dagger.module.NewsRepositoryModule
import com.abhat.news.utils.Constants

/**
 * Created by Anirudh Uppunda on 8/3/18.
 */
class App: Application() {

    lateinit var dataComponent: DataComponent
    lateinit var newsRepositoryComponent: NewsRepositoryComponent

    companion object {
        private lateinit var context: App

        fun getContext(): Context {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        this.initializeInjector()
    }

    private fun initializeInjector() {
        dataComponent = DaggerDataComponent.builder()
                .appModule(AppModule(this))
                .dataModule(DataModule(Constants.API_URL))
                .build()

        newsRepositoryComponent = dataComponent.plus(NewsRepositoryModule())
    }
}