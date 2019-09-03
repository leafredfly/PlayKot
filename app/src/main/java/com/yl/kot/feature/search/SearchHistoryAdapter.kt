package com.yl.kot.feature.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yl.kot.App
import com.yl.kot.Page
import com.yl.kot.R
import com.yl.kot.base.BaseEmptyRecyclerAdapter
import com.yl.kot.base.BaseViewHolder
import com.yl.kot.data.entity.SearchHistory

/**
 * Author: Want-Sleep
 * Date: 2019/07/31
 * Desc:
 */

class SearchHistoryAdapter : BaseEmptyRecyclerAdapter<SearchHistoryAdapter.HotWordViewHolder, SearchHistory>() {

    override fun onCreateCommonViewHolder(parent: ViewGroup, viewType: Int): HotWordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_history, parent, false)
        return HotWordViewHolder(view)
    }

    override fun onCreateEmptyViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_search_history_empty, parent, false)
        return EmptyViewHolder(view)
    }

    inner class HotWordViewHolder(itemView: View) : BaseViewHolder<SearchHistory>(itemView) {

        private lateinit var tvContent: TextView

        override fun onViewBinding(itemView: View) {
            tvContent = itemView.findViewById(R.id.tv_search_history_content)
        }

        override fun onDataBinding(data: SearchHistory) {
            tvContent.text = data.keyWord
        }

        override fun onHolderClick(data: SearchHistory) {
            App.getInstance().topActivity().finish()
            Page.toSearchDetail(data.keyWord)
        }
    }
}