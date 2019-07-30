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

class HomeArticleItemDecoration : RecyclerView.ItemDecoration() {

    private val mCellSpace: Int = ScreenUtils.dp2px(8)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val viewPosition = parent.getChildLayoutPosition(view)
        if (viewPosition == 0) {
            outRect.top = mCellSpace
        }
        outRect.bottom = mCellSpace
        outRect.left = mCellSpace
        outRect.right = mCellSpace
    }
}