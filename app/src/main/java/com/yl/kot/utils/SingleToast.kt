package com.yl.kot.utils

import android.widget.Toast

import com.yl.kot.App


/**
 * Author: Unknown
 * Date: 2019/07/26
 * Desc:
 */
class SingleToast {
    companion object {
        private var mToast: Toast? = null

        fun showToast(resId: Int) {
            showToast(App.getInstance().getString(resId))
        }

        fun showToast(msg: String) {
            mToast?.cancel()
            mToast = Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT)
            mToast?.show()
        }
    }
}
