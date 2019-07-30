package com.yl.kot.utils

import android.util.TypedValue
import com.yl.kot.App

/**
 * Author: Want-Sleep
 * Date: 2019/07/30
 * Desc:
 */

class ScreenUtils {
    companion object {
        fun dp2px(dp: Int): Int {
            val r = App.getInstance().resources
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics).toInt()
        }
    }
}