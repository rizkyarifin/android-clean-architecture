package sample.base.app.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_news.view.*
import sample.base.app.R
import sample.base.app.data.model.Article
import sample.base.app.utils.ext.loadFromUrl

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(article: Article) {
        itemView.txt_news_name.text = article.title

        article.urlToImage?.let {
            itemView.img_news_banner.loadFromUrl(
                it,
                itemView.context
            )
        }
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)
            return NewsViewHolder(view)
        }
    }
}