package com.yl.kot.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yl.kot.R

/**
 * Author: Want-Sleep
 * Date: 2019/07/26
 * Desc:
 */
abstract class BaseEmptyRecyclerAdapter<VH : BaseViewHolder<T>, T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_COMMON = 1
        private const val TYPE_EMPTY = 2
    }

    protected val mData = ArrayList<T>()
    private var mDataSetChanged = false

    override fun getItemCount(): Int {
        if (!mDataSetChanged) {
            return 0
        }
        return if (mData.size > 0) mData.size else 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (mData.size > 0) TYPE_COMMON else TYPE_EMPTY
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_EMPTY -> onCreateEmptyViewHolder(parent, viewType)
            else -> onCreateCommonViewHolder(parent, viewType)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (mData.size > 0) {
            holder as VH
            holder.onDataBinding(mData[position])
            holder.itemView.setOnClickListener {
                holder.onHolderClick(mData[position])
            }
        }
    }

    fun refresh(dataList: List<T>) {
        mData.clear()
        mData.addAll(dataList)
        mDataSetChanged = true
        notifyDataSetChanged()
    }

    fun loadMore(dataList: List<T>) {
        val oldSize = mData.size
        mData.addAll(dataList)
        mDataSetChanged = true
        notifyItemRangeInserted(oldSize, mData.size - 1)
    }

    open fun onCreateEmptyViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_list_empty, parent, false)
        return EmptyViewHolder(view)
    }

    abstract fun onCreateCommonViewHolder(parent: ViewGroup, viewType: Int): VH

    inner class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}