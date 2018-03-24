package com.abhat.news

import com.abhat.news.data.model.Article
import com.abhat.news.data.model.NewsApiResponse
import com.abhat.news.data.source.NewsRepository
import com.abhat.news.ui.NewsContract
import com.abhat.news.ui.NewsPresenter
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Created by Anirudh Uppunda on 19/3/18.
 */
class NewsPresenterTest {
    private lateinit var mockNewsView: NewsContract.View
    private lateinit var mockNewsRepository: NewsRepository
    private lateinit var newsPresenter: NewsContract.Presenter
    private lateinit var testSubscriber: TestSubscriber<NewsApiResponse>
    private val API_KEY = "ENTER YOUR NEWS API KEY HERE"
    private val pageSize = "30"
    private val country = "IN"
    private val source = "bbc-news"
    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline()}
        mockNewsView = mock(NewsContract.View::class.java)
        mockNewsRepository  = mock(NewsRepository::class.java)
        testSubscriber = TestSubscriber()
        newsPresenter = NewsPresenter(mockNewsRepository, mockNewsView)
    }

    @Test
    fun `should show loading when presenter is subscribed`() {
        `when`(mockNewsRepository.getNews(country, pageSize, API_KEY)).thenReturn(Single.fromCallable ({
            NewsApiResponse("", 30, listOf())
        }))
        newsPresenter.subscribe(country)
        verify(mockNewsView).showLoading()
    }

    @Test
    fun `should hide loading when presenter is finished loading news`() {
        `when`(mockNewsRepository.getNews(country, pageSize, API_KEY)).thenReturn(Single.fromCallable<NewsApiResponse> ({
            NewsApiResponse("", 0, listOf())
        }))
        newsPresenter.subscribe(country)
        // test fails whithout below line, strange.
        verify(mockNewsRepository).getNews(country, pageSize, API_KEY)
        verify(mockNewsView).hideLoading()
    }

    @Test
    fun `should hide loading when presenter throws error`() {
        `when`(mockNewsRepository.getNews(country, pageSize, API_KEY)).thenReturn(Single.error<NewsApiResponse>(Throwable()))
        newsPresenter.subscribe(country)
        verify(mockNewsView).hideLoading()
    }

    @Test
    fun `should show news on successfull news api response` () {
        val newsApiResponse = NewsApiResponse("", 0, listOf())
        `when`(mockNewsRepository.getNews(country, pageSize, API_KEY)).thenReturn(Single.fromCallable<NewsApiResponse> ({
            newsApiResponse
        }))
        newsPresenter.subscribe(country)
        verify(mockNewsRepository).getNews(country, pageSize, API_KEY)
        verify(mockNewsView).showNewsData(newsApiResponse)
    }


    @Test
    fun `should show loading when presenter is subscribed for source`() {
        `when`(mockNewsRepository.getNewsFromSource("bbc-news", pageSize, API_KEY)).thenReturn(Single.fromCallable ({
            NewsApiResponse("", 30, listOf())
        }))
        newsPresenter.subscribeForSources(source)
        verify(mockNewsView).showLoading()
    }


    @Test
    fun `should hide loading when presenter is finished loading news from source`() {
        `when`(mockNewsRepository.getNewsFromSource(source, pageSize, API_KEY)).thenReturn(Single.fromCallable<NewsApiResponse> ({
            NewsApiResponse("", 0, listOf())
        }))
        newsPresenter.subscribeForSources(source)
        // test fails whithout below line, strange.
        verify(mockNewsRepository).getNewsFromSource(source, pageSize, API_KEY)
        verify(mockNewsView).hideLoading()
    }

    @Test
    fun `should hide loading when presenter throws error from source`() {
        `when`(mockNewsRepository.getNewsFromSource(source, pageSize, API_KEY)).thenReturn(Single.error<NewsApiResponse>(Throwable()))
        newsPresenter.subscribeForSources(source)
        verify(mockNewsView).hideLoading()
    }

    @Test
    fun `should show news from source on successfull news api response` () {
        val newsApiResponse = NewsApiResponse("", 0, listOf())
        `when`(mockNewsRepository.getNewsFromSource(source, pageSize, API_KEY)).thenReturn(Single.fromCallable<NewsApiResponse> ({
            newsApiResponse
        }))
        newsPresenter.subscribeForSources(source)
        verify(mockNewsRepository).getNewsFromSource(source, pageSize, API_KEY)
        verify(mockNewsView).showNewsData(newsApiResponse)
    }

}