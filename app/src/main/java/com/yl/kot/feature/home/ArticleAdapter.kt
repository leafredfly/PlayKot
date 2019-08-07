package com.yl.kot.feature.home

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.yl.kot.Page
import com.yl.kot.R
import com.yl.kot.base.BaseEmptyRecyclerAdapter
import com.yl.kot.base.BaseViewHolder
import com.yl.kot.data.entity.Article

/**
 * Author: Want-Sleep
 * Date: 2019/07/29
 * Desc:
 */

class ArticleAdapter : BaseEmptyRecyclerAdapter<ArticleAdapter.ArticleViewHolder, Article>() {

    override fun onCreateCommonViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_article, parent, false)
        return ArticleViewHolder(view)
    }

    inner class ArticleViewHolder(itemView: View) : BaseViewHolder<Article>(itemView) {

        private val mTagColorArray = arrayOf("#4CAF50", "#E64A19", "#7B1FA2", "#FF9800")

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
        }

        override fun onDataBinding(data: Article) {
            if (data.top) {
                val ss = SpannableString(mContext.getString(R.string.home_top_article_tag, convertHtml2String(data.title)))
                // 3 = prefix text length
                ss.setSpan(ForegroundColorSpan(Color.RED), 0, 3, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
                tvTitle.text = ss
            } else {
                tvTitle.text = convertHtml2String(data.title)
            }
            tvAuthor.text = data.author
            tvDate.text = data.niceDate
            // set tags
            llTagGroup.removeAllViews()
            if (data.tagList.isNotEmpty()) {
                llTagGroup.visibility = View.VISIBLE
                for (tag in data.tagList) {
                    val tagView = LayoutInflater.from(mContext)
                        .inflate(R.layout.view_tag_text_view, llTagGroup, false)
                            as TextView
                    tagView.text = tag.name
                    val gradientDrawable = tagView.background as GradientDrawable
                    gradientDrawable.setColor(Color.parseColor(mTagColorArray.random()))
                    llTagGroup.addView(tagView)
                }
            } else {
                llTagGroup.visibility = View.GONE
            }
        }

        override fun onHolderClick(data: Article) {
            Page.toWebSite(data.link, convertHtml2String(data.title).toString())
        }

        private fun convertHtml2String(html: String): Spanned {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
            else Html.fromHtml(html)
        }
    }
}