package com.yl.kot.base

import androidx.annotation.StringRes
import com.yl.kot.App

/**
 * Author: Want-Sleep
 * Date: 2019/07/25
 * Desc:
 */
interface IBaseView {
    fun getStringValue(@StringRes resId: Int): String {
        return App.getInstance().getString(resId)
    }

    fun getStringValue(@StringRes resId: Int, vararg formatArgs: Any): String {
        return App.getInstance().getString(resId, *formatArgs)
    }
}
