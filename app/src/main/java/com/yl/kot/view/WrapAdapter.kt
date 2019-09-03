package com.yl.kot.view

import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Author: Want-Sleep
 * Date: 2019/09/02
 * Desc:
 */

class WrapAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val KEY_START = 100000
    }

    private val mItemViews = SparseArray<View>()

    override fun getItemViewType(position: Int): Int = mItemViews.keyAt(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WrapViewHolder(mItemViews.get(viewType))
    }

    override fun getItemCount(): Int = mItemViews.size()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    fun addItemView(view: View) {
        mItemViews.put(mItemViews.size() + KEY_START, view)
    }

    inner class WrapViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}