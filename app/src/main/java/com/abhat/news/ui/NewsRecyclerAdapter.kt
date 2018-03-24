package com.abhat.news.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.abhat.news.R
import com.abhat.news.data.model.Article
import com.bumptech.glide.Glide

/**
 * Created by Anirudh Uppunda on 8/3/18.
 */
class NewsRecyclerAdapter(
        private val context: Context,
        private var newsData: List<Article>): RecyclerView.Adapter<NewsRecyclerAdapter.MyViewHolder>() {

    fun setNewsData(newsData: List<Article>) {
        this.newsData = newsData
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val headline: String? = newsData[position].title
        val imageUrl: String? = newsData[position].urlToImage
        val source: String? = newsData[position].source.name
        holder.bind(imageUrl, headline, source)
    }

    override fun getItemCount(): Int {
        return newsData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder{
        val layoutInflater = LayoutInflater.from(parent?.context)
        return MyViewHolder(layoutInflater.inflate(R.layout.top_news_single_row, parent, false))
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val newsImage = itemView.findViewById<ImageView>(R.id.top_news_image)
        val newsHeadlineText = itemView.findViewById<TextView>(R.id.top_news_headline)
        val newsLayout = itemView.findViewById<LinearLayout>(R.id.news_layout)
        val source = itemView.findViewById<TextView>(R.id.source)
        fun bind(newsImageUrl: String?, newsHeadline: String?, sourceString: String?) {
            newsHeadlineText.text = newsHeadline
            source.text = sourceString ?: ""
            loadImage(newsImageUrl, newsImage)
            newsLayout.setOnClickListener {
                openUrl(newsData[adapterPosition].url)
            }
        }
    }

    private fun openUrl(url: String) {
        val i: Intent = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        context.startActivity(i)
    }

    private fun loadImage(newsImageUrl: String?, newsImageView: ImageView) {
        Glide.with(context)
                .load(newsImageUrl)
                .into(newsImageView)
    }
}