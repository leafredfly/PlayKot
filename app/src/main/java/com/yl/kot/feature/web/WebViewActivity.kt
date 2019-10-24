package com.yl.kot.feature.web

import android.annotation.SuppressLint
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yl.kot.Constants
import com.yl.kot.MemoryData
import com.yl.kot.R
import com.yl.kot.base.BaseActivity
import com.yl.kot.data.entity.Article
import com.yl.kot.feature.collection.CollectionContract
import com.yl.kot.feature.collection.CollectionPresenter

/**
 * Author: Want-Sleep
 * Date: 2019/07/30
 * Desc:
 */

class WebViewActivity : BaseActivity(), CollectionContract.View {

    private lateinit var flWebContainer: FrameLayout
    private lateinit var webView: WebView
    private lateinit var fabCollect: FloatingActionButton

    private val mCollectionPresenter: CollectionContract.Presenter by lazy {
        CollectionPresenter(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        flWebContainer.removeAllViews()
        webView.removeAllViews()
        webView.destroy()
    }

    override fun getLayoutId(): Int = R.layout.activity_webview

    override fun addLifecycleObserver() {
        lifecycle.addObserver(mCollectionPresenter)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        title = intent.getStringExtra(Constants.EXTRA_WEBSITE_TITLE)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        flWebContainer = findViewById(R.id.fl_web_container)
        fabCollect = findViewById(R.id.fab_article_collect)
        webView = WebView(this)

        flWebContainer.addView(webView, 0,
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        )

        // WebView Setting
        val webSettings = webView.settings
        webSettings.domStorageEnabled = true
        webSettings.javaScriptEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }

        val url = intent.getStringExtra(Constants.EXTRA_URL)
        webView.loadUrl(url)

        fabCollect.setOnClickListener {
            MemoryData.getBrowsedArticle()?.let {
                mCollectionPresenter.collectOrCancel(it)
            }
        }
        MemoryData.getBrowsedArticle()?.let {
            mCollectionPresenter.queryArticleHasCollected(it.id)
        }
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

    override fun onArticleHasCollected(hasCollected: Boolean) {
        fabCollect.visibility = View.VISIBLE
        if (hasCollected) {
            fabCollect.setImageResource(R.drawable.ic_collection_checked)
        } else {
            fabCollect.setImageResource(R.drawable.ic_collection_unchecked)
        }
    }

    override fun showArticle(articleList: List<Article>) {

    }
}