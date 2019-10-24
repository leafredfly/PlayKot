package com.yl.kot.feature.collection

import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.yl.kot.MemoryData
import com.yl.kot.Page
import com.yl.kot.R
import com.yl.kot.base.BaseEmptyRecyclerAdapter
import com.yl.kot.base.BaseViewHolder
import com.yl.kot.data.entity.Article

/**
 * Author: Want-Sleep
 * Date: 2019/10/24
 * Desc:
 */

class CollectionArticleAdapter : BaseEmptyRecyclerAdapter<CollectionArticleAdapter.ArticleViewHolder, Article>() {

    override fun onCreateCommonViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_article, parent, false)
        return ArticleViewHolder(view)
    }

    inner class ArticleViewHolder(itemView: View) : BaseViewHolder<Article>(itemView) {

        private lateinit var tvTitle: TextView
        private lateinit var tvAuthor: TextView
        private lateinit var tvDate: TextView
        private lateinit var llTagGroup: LinearLayout
        private lateinit var mContext: Context

        override fun onViewBinding(itemView: View) {
            mContext = itemView.context
            tvTitle = itemView.findViewById(R.id.tv_home_article_title)
            tvAuthor = itemView.findViewById(R.id.tv_home_article_author)
            tvDate = itemView.findViewById(R.id.tv_home_article_date)
            llTagGroup = itemView.findViewById(R.id.ll_home_article_tags)
            llTagGroup.visibility = View.GONE
        }

        override fun onDataBinding(data: Article) {
            tvTitle.text = convertHtml2String(data.title)
            tvAuthor.text = if (data.author.isNotEmpty()) {
                data.author
            } else {
                data.shareUser
            }
            tvDate.text = data.getFormatCollectionTime()
        }

        override fun onHolderClick(data: Article) {
            MemoryData.setBrowsedArticle(data)
            Page.toWebSite(data.link, convertHtml2String(data.title).toString())
        }

        private fun convertHtml2String(html: String): Spanned {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
            else Html.fromHtml(html)
        }
    }
}