package com.yl.kot.view.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yl.kot.utils.ScreenUtils

/**
 * Author: Want-Sleep
 * Date: 2019/07/30
 * Desc:
 */

class HotWordItemDecoration : RecyclerView.ItemDecoration() {

    private val mCellSpace: Int = ScreenUtils.dp2px(8)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.top = 0
        outRect.bottom = mCellSpace
        outRect.left = 0
        outRect.right = mCellSpace
    }
}