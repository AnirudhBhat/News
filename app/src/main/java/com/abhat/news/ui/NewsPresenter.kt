package com.abhat.news.ui

import com.abhat.news.data.model.NewsApiResponse
import com.abhat.news.data.source.NewsRepository
import com.abhat.news.utils.Constants.NEWS_API_KEY
import com.abhat.news.utils.Constants.PAGE_SIZE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Anirudh Uppunda on 8/3/18.
 */
class NewsPresenter @Inject constructor(
        private val newsRepository: NewsRepository,
        private val newsView: NewsContract.View): NewsContract.Presenter {

    var TAG = this.javaClass.simpleName

    private val disposable: CompositeDisposable = CompositeDisposable()

    @Inject
    fun setupListeners() {
        newsView.setPresenter(this)
    }


    override fun subscribe(country: String) {
        newsView.showLoading()
        getNews(country)
    }

    override fun subscribeForSources(sources: String) {
        newsView.showLoading()
        getNewsFromSources(sources)
    }

    override fun unSubscribe() {
        disposable.dispose()
    }

    override fun getNews(country: String) {
        newsRepository.getNews(country, PAGE_SIZE, NEWS_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    processNewsApiResponse(it)
                }, {
                    showError(it)
                })
    }

    private fun getNewsFromSources(sources: String) {
        newsRepository.getNewsFromSource(sources, PAGE_SIZE, NEWS_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    processNewsApiResponse(it)
                }, {
                    showError(it)
                })
    }

    private fun processNewsApiResponse(newsApiResponse: NewsApiResponse) {
        newsView.hideLoading()
        newsView.showNewsData(newsApiResponse)
    }

    private fun showError(error: Throwable) {
        newsView.hideLoading()
        newsView.showError(error)
    }
}