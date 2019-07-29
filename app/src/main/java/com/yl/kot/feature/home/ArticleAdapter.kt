package com.yl.kot.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yl.kot.R
import com.yl.kot.base.BaseRecyclerAdapter
import com.yl.kot.base.BaseViewHolder
import com.yl.kot.data.entity.Article

/**
 * Author: Want-Sleep
 * Date: 2019/07/29
 * Desc:
 */

class ArticleAdapter : BaseRecyclerAdapter<ArticleAdapter.ArticleViewHolder, Article>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_article, parent, false)
        return ArticleViewHolder(view)
    }

    inner class ArticleViewHolder(itemView: View) : BaseViewHolder<Article>(itemView) {

        private lateinit var tvTitle: TextView

        override fun onViewBinding(itemView: View) {
            tvTitle = itemView.findViewById(R.id.tv_home_article_title)
        }

        override fun onDataBinding(data: Article) {
            tvTitle.text = data.title
        }

        override fun onHolderClick(data: Article) {

        }
    }
}