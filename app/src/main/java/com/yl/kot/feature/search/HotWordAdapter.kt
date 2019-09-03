package com.yl.kot.feature.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.yl.kot.App
import com.yl.kot.Page
import com.yl.kot.R
import com.yl.kot.base.BaseRecyclerAdapter
import com.yl.kot.base.BaseViewHolder
import com.yl.kot.data.entity.HotWord

/**
 * Author: Want-Sleep
 * Date: 2019/07/31
 * Desc:
 */

class HotWordAdapter : BaseRecyclerAdapter<HotWordAdapter.HotWordViewHolder, HotWord>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotWordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_hot_word, parent, false)
        return HotWordViewHolder(view)
    }

    inner class HotWordViewHolder(itemView: View) : BaseViewHolder<HotWord>(itemView) {

        private lateinit var tvContent: TextView

        override fun onViewBinding(itemView: View) {
            tvContent = itemView.findViewById(R.id.tv_search_hot_word_content)
        }

        override fun onDataBinding(data: HotWord) {
            tvContent.text = data.name
        }

        override fun onHolderClick(data: HotWord) {
            App.getInstance().topActivity().finish()
            Page.toSearchDetail(data.name)
        }
    }
}