package com.yl.kot.base

import androidx.recyclerview.widget.RecyclerView

/**
 * Author: Want-Sleep
 * Date: 2019/07/26
 * Desc:
 */
abstract class BaseRecyclerAdapter<VH : BaseViewHolder<T>, T> : RecyclerView.Adapter<VH>() {

    protected val mData = ArrayList<T>()

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onDataBinding(mData[position])
        holder.itemView.setOnClickListener {
            holder.onHolderClick(mData[position])
        }
    }

    fun refresh(dataList: List<T>) {
        mData.clear()
        mData.addAll(dataList)
        notifyDataSetChanged()
    }

    fun loadMore(dataList: List<T>) {
        val oldSize = mData.size
        mData.addAll(dataList)
        notifyItemRangeInserted(oldSize, mData.size - 1)
    }
}