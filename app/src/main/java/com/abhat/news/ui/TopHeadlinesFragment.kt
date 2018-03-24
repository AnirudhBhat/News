package com.abhat.news.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.abhat.news.App
import com.abhat.news.R
import com.abhat.news.data.model.NewsApiResponse
import javax.inject.Inject

/**
 * Created by Anirudh Uppunda on 8/3/18.
 */
class TopHeadlinesFragment: Fragment(), NewsContract.View {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: NewsRecyclerAdapter
    private lateinit var newsRecyclerView: RecyclerView

    private lateinit var presenter: NewsContract.Presenter

    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private var isFromSource: Boolean = false
    private lateinit var source: String

    @Inject
    lateinit var newsPresenter: NewsPresenter

    companion object {
        fun newInstance(isFromSource: Boolean, source: String): TopHeadlinesFragment {
            val topHeadlinesFragment: TopHeadlinesFragment = TopHeadlinesFragment()
            val args: Bundle = Bundle()
            args.putBoolean("isFromSource", isFromSource)
            args.putString("source", source)
            topHeadlinesFragment.arguments = args
            return topHeadlinesFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isFromSource = arguments?.getBoolean("isFromSource")!!
        source = arguments?.getString("source")!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_top_news, container, false)
        progressBar = view.findViewById(R.id.progress_bar)
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener {
            presenter.subscribe("IN")
        }
        setupRecyclerView(view)
        (activity?.application as App).newsRepositoryComponent
                .plus(NewsPresenterModule(this))
                .inject(this)
        return view
    }

    private fun setupRecyclerView(view: View) {
        newsRecyclerView = view.findViewById(R.id.top_news_recycler_view)
        layoutManager = LinearLayoutManager(activity)
        newsRecyclerView.layoutManager = layoutManager
        adapter = NewsRecyclerAdapter(activity!!, listOf())
        newsRecyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        if (isFromSource) {
            presenter.subscribeForSources(source)
        } else {
            presenter.subscribe("IN")
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.unSubscribe()
    }

    override fun setPresenter(presenter: NewsContract.Presenter) {
        this.presenter = presenter
    }

    override fun showNewsData(newsApiResponse: NewsApiResponse) {
        adapter.setNewsData(newsApiResponse.articles)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        if (swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun showError(error: Throwable) {
        error.printStackTrace()
    }
}