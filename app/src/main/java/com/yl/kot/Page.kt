package com.yl.kot

import android.content.Intent
import com.yl.kot.feature.home.HomeActivity
import com.yl.kot.feature.login.LoginActivity

/**
 * Author: Want-Sleep
 * Date: 2019/07/29
 * Desc:
 */

class Page {
    companion object {
        fun toLogin() {
            val activity = App.getInstance().topActivity()
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivityForResult(intent, LoginActivity.REQUEST_CODE_LOGIN)
        }

        fun toHome() {
            val activity = App.getInstance().topActivity()
            val intent = Intent(activity, HomeActivity::class.java)
            activity.startActivity(intent)
        }
    }
}