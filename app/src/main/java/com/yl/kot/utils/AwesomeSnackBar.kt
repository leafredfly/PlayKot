package com.yl.kot.utils

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.yl.kot.App
import com.yl.kot.R


/**
 * Author: Unknown
 * Date: 2019/07/26
 * Desc:
 */
class AwesomeSnackBar {
    companion object {
        fun show(resId: Int) {
            show(App.getInstance().getString(resId))
        }

        fun show(msg: String) {
            val context = App.getInstance()
            val activityViewRoot = App.getInstance().topActivity().findViewById(android.R.id.content) as View
            Snackbar.make(activityViewRoot, msg, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(ContextCompat.getColor(context, R.color.colorPrimary))
                .setTextColor(ContextCompat.getColor(context, R.color.text_white))
                .show()
        }
    }
}
