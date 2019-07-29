package com.yl.kot.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Author: Want-Sleep
 * Date: 2019/07/26
 * Desc:
 */
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    init {
        this.onViewBinding(itemView)
    }

    abstract fun onViewBinding(itemView: View)

    abstract fun onDataBinding(data: T)
}