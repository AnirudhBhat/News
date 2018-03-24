package com.abhat.news

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.abhat.news.ui.TopHeadlinesFragment
import com.abhat.news.utils.Constants

class MainActivity : AppCompatActivity() {

    private val drawer: DrawerLayout by lazy { findViewById<DrawerLayout>(R.id.drawer_layout) }
    private val navigationView: NavigationView by lazy { findViewById<NavigationView>(R.id.navigation_view) }
    private val toolBar: android.support.v7.widget.Toolbar by lazy { findViewById<android.support.v7.widget.Toolbar>(R.id.toolbar) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolBar()
        initNavigationDrawer()
        loadFragment(TopHeadlinesFragment.newInstance(false, ""))
    }


    private fun setupToolBar() {
        setSupportActionBar(toolBar)
    }

    private fun initNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.headlines_news -> {
                    drawer.closeDrawers()
                    loadFragment(TopHeadlinesFragment.newInstance(false, ""))
                }
                R.id.bbc_news -> {
                    drawer.closeDrawers()
                    loadFragment(TopHeadlinesFragment.newInstance(true, Constants.BBC_NEWS))
                }
                R.id.financial_times_news -> {
                    drawer.closeDrawers()
                    loadFragment(TopHeadlinesFragment.newInstance(true, Constants.FINANCIAL_TIMES))
                }
                R.id.national_geographic_news -> {
                    drawer.closeDrawers()
                    loadFragment(TopHeadlinesFragment.newInstance(true, Constants.NATIONAL_GEOGRAPHIC))
                }
                R.id.techcrunch_news -> {
                    drawer.closeDrawers()
                    loadFragment(TopHeadlinesFragment.newInstance(true, Constants.TECHCRUNCH))
                }
                R.id.the_economist_news -> {
                    drawer.closeDrawers()
                    loadFragment(TopHeadlinesFragment.newInstance(true, Constants.THE_ECONOMIST))
                }
                R.id.the_hindu_news -> {
                    drawer.closeDrawers()
                    loadFragment(TopHeadlinesFragment.newInstance(true, Constants.THE_HINDU))
                }
                R.id.the_times_of_india_news -> {
                    drawer.closeDrawers()
                    loadFragment(TopHeadlinesFragment.newInstance(true, Constants.THE_TIMES_OF_INDIA))
                }
                R.id.reddit_news -> {
                    drawer.closeDrawers()
                    loadFragment(TopHeadlinesFragment.newInstance(true, Constants.REDDIT))
                }
                R.id.hacker_news -> {
                    drawer.closeDrawers()
                    loadFragment(TopHeadlinesFragment.newInstance(true, Constants.HACKER_NEWS))
                }
                R.id.espn_news -> {
                    drawer.closeDrawers()
                    loadFragment(TopHeadlinesFragment.newInstance(true, Constants.ESPN))
                }
                else -> true
            }
        }

        val actionBarToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(this, drawer, toolBar, R.string.drawer_open, R.string.drawer_close) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }
        }
        drawer.addDrawerListener(actionBarToggle)
        actionBarToggle.syncState()
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        return true
    }
}
