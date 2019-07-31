package com.yl.kot.feature.search

import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.yl.kot.R
import com.yl.kot.base.BaseActivity
import com.yl.kot.data.entity.Article
import com.yl.kot.data.entity.HotWord
import com.yl.kot.view.decoration.HotWordItemDecoration

/**
 * Author: Want-Sleep
 * Date: 2019/07/31
 * Desc:
 */

class SearchActivity : BaseActivity(), SearchContract.View {

    private val mSearchPresenter: SearchContract.Presenter by lazy {
        SearchPresenter(this)
    }
    private val mHotWordAdapter: HotWordAdapter by lazy {
        HotWordAdapter()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getLayoutId(): Int = R.layout.activity_search

    override fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val rvHotWords = findViewById<RecyclerView>(R.id.rv_search_hot_words)
        val flexBoxLayoutManager = FlexboxLayoutManager(this)
        rvHotWords.layoutManager = flexBoxLayoutManager
        rvHotWords.addItemDecoration(HotWordItemDecoration())
        rvHotWords.adapter = mHotWordAdapter

        mSearchPresenter.getHotWords()
    }

    override fun showHotWords(hotWordList: List<HotWord>) {
        mHotWordAdapter.refresh(hotWordList)
    }

    override fun showSearchResult(articleList: List<Article>) {

    }
}