package com.abhat.news.ui

import com.abhat.news.data.model.NewsApiResponse

/**
 * Created by Anirudh Uppunda on 8/3/18.
 */
interface NewsContract {

    interface Presenter {
        fun subscribe(country: String)
        fun subscribeForSources(sources: String)
        fun unSubscribe()
        fun getNews(country: String)
    }

    interface View {
        fun setPresenter(presenter: NewsContract.Presenter)
        fun showNewsData(newsApiResponse: NewsApiResponse)
        fun showLoading()
        fun hideLoading()
        fun showError(error: Throwable)
    }
}