package com.yl.kot

import android.content.Intent
import android.net.Uri
import com.yl.kot.feature.home.HomeActivity
import com.yl.kot.feature.login.LoginActivity
import com.yl.kot.utils.SingleToast

/**
 * Author: Want-Sleep
 * Date: 2019/07/29
 * Desc:
 */

class Page {
    companion object {
        /**
         * to登录
         */
        fun toLogin() {
            val activity = App.getInstance().topActivity()
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivityForResult(intent, LoginActivity.REQUEST_CODE_LOGIN)
        }

        /**
         * to首页
         */
        fun toHome() {
            val activity = App.getInstance().topActivity()
            val intent = Intent(activity, HomeActivity::class.java)
            activity.startActivity(intent)
        }

        /**
         * to外部浏览器
         */
        fun toDefaultBrowser(url: String) {
            try {
                val activity = App.getInstance().topActivity()
                val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                activity.startActivity(i)
            } catch (e: Exception) {
                SingleToast.showToast(R.string.error_bad_link)
            }
        }
    }
}