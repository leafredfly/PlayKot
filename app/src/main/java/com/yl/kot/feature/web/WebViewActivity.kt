package com.yl.kot.feature.web

import android.annotation.SuppressLint
import android.view.MenuItem
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.FrameLayout
import com.yl.kot.Constants
import com.yl.kot.R
import com.yl.kot.base.BaseActivity

/**
 * Author: Want-Sleep
 * Date: 2019/07/30
 * Desc:
 */

class WebViewActivity : BaseActivity() {

    private lateinit var flWebContainer: FrameLayout
    private lateinit var webView: WebView

    override fun onDestroy() {
        super.onDestroy()
        flWebContainer.removeAllViews()
        webView.removeAllViews()
        webView.destroy()
    }

    override fun getLayoutId(): Int = R.layout.activity_webview

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        title = intent.getStringExtra(Constants.EXTRA_WEBSITE_TITLE)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        flWebContainer = findViewById(R.id.fl_web_container)
        webView = WebView(this)
        flWebContainer.addView(webView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        // WebView Setting
        val webSettings = webView.settings
        webSettings.domStorageEnabled = true
        webSettings.javaScriptEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true

        val url = intent.getStringExtra(Constants.EXTRA_URL)
        webView.loadUrl(url)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}