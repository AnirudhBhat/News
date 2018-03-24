package com.abhat.news.dagger.module

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * Created by Anirudh Uppunda on 20/2/18.
 */
@Module
internal class AppModule(private val context: Context) {

    @Provides
    internal fun provideContext() = context

}