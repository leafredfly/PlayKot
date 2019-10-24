package com.yl.kot

import android.content.Intent
import android.net.Uri
import com.yl.kot.feature.collection.CollectionListActivity
import com.yl.kot.feature.home.HomeActivity
import com.yl.kot.feature.search.SearchActivity
import com.yl.kot.feature.search.SearchDetailActivity
import com.yl.kot.feature.web.WebViewActivity
import com.yl.kot.utils.AwesomeSnackBar

/**
 * Author: Want-Sleep
 * Date: 2019/07/29
 * Desc:
 */

class Page {
    companion object {

        /**
         * to首页
         */
        fun toHome() {
            val activity = App.getInstance().topActivity()
            activity.startActivity(HomeActivity::class.java)
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
                AwesomeSnackBar.show(R.string.error_bad_link)
            }
        }

        /**
         * to WebView
         *
         */
        fun toWebSite(url: String, websiteTitle: String) {
            val activity = App.getInstance().topActivity()
            val intent = Intent(activity, WebViewActivity::class.java)
            intent.putExtra(Constants.EXTRA_URL, url)
            intent.putExtra(Constants.EXTRA_WEBSITE_TITLE, websiteTitle)
            activity.startActivity(intent)
        }

        /**
         * to Search
         *
         */
        fun toSearch() {
            val activity = App.getInstance().topActivity()
            activity.startActivity(SearchActivity::class.java)
        }

        /**
         * to Search detail
         *
         */
        fun toSearchDetail(keyword: String) {
            val activity = App.getInstance().topActivity()
            val intent = Intent(activity, SearchDetailActivity::class.java)
            intent.putExtra(Constants.EXTRA_SEARCH_KEY, keyword)
            activity.startActivity(intent)
        }

        /**
         * 前往我的收藏页面
         *
         */
        fun toCollection() {
            val activity = App.getInstance().topActivity()
            activity.startActivity(CollectionListActivity::class.java)
        }
    }
}