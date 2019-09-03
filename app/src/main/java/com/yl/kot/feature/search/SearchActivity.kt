package com.yl.kot.feature.search

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager
import com.yl.kot.Page
import com.yl.kot.R
import com.yl.kot.base.BaseActivity
import com.yl.kot.data.entity.Article
import com.yl.kot.data.entity.HotWord
import com.yl.kot.data.entity.SearchHistory
import com.yl.kot.view.WrapAdapter
import com.yl.kot.view.decoration.HotWordItemDecoration

/**
 * Author: Want-Sleep
 * Date: 2019/07/31
 * Desc:
 */

class SearchActivity : BaseActivity(), SearchContract.View {

    private lateinit var searchView: SearchView
    private lateinit var ivHistoryClear: ImageView

    private val mSearchPresenter: SearchContract.Presenter by lazy {
        SearchPresenter(this)
    }
    private val mHotWordAdapter: HotWordAdapter by lazy {
        HotWordAdapter()
    }

    private val mSearchHistoryAdapter: SearchHistoryAdapter by lazy {
        SearchHistoryAdapter()
    }

    override fun onBackPressed() {
        if (!searchView.isIconified) {
            searchView.isIconified = true
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.isIconified = false
        searchView.queryHint = getString(R.string.search_input_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                s?.let {
                    finish()
                    Page.toSearchDetail(it)
                }
                return true
            }

            override fun onQueryTextChange(s: String?): Boolean {
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getLayoutId(): Int = R.layout.activity_search

    override fun showDataLoadFailView(): Boolean = false

    override fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val rvWrapView = findViewById<RecyclerView>(R.id.rv_search_wrap)
        val wrapLayoutManager = LinearLayoutManager(this)
        rvWrapView.layoutManager = wrapLayoutManager
        val wrapAdapter = WrapAdapter()
        rvWrapView.adapter = wrapAdapter

        val historyView = LayoutInflater.from(this).inflate(R.layout.part_search_history, rvWrapView, false)
        val rvSearchHistory = historyView.findViewById<RecyclerView>(R.id.rv_search_history)
        ivHistoryClear = historyView.findViewById(R.id.iv_search_history_clear)
        ivHistoryClear.setOnClickListener {
            mSearchPresenter.clearSearchHistory()
        }
        val searchHistoryLayoutManager = FlexboxLayoutManager(this)
        rvSearchHistory.layoutManager = searchHistoryLayoutManager
        rvSearchHistory.addItemDecoration(HotWordItemDecoration())
        rvSearchHistory.adapter = mSearchHistoryAdapter
        wrapAdapter.addItemView(historyView)

        val hotWordView = LayoutInflater.from(this).inflate(R.layout.part_search_hot, rvWrapView, false)
        val rvHotWords = hotWordView.findViewById<RecyclerView>(R.id.rv_search_hot_words)
        val hotWordLayoutManager = FlexboxLayoutManager(this)
        rvHotWords.layoutManager = hotWordLayoutManager
        rvHotWords.addItemDecoration(HotWordItemDecoration())
        rvHotWords.adapter = mHotWordAdapter
        wrapAdapter.addItemView(hotWordView)

        mSearchPresenter.getHotWords()
        mSearchPresenter.getSearchHistory()
    }

    override fun showHotWords(hotWordList: List<HotWord>) {
        mHotWordAdapter.refresh(hotWordList)
    }

    override fun showSearchHistory(searchHistory: List<SearchHistory>) {
        if (searchHistory.isEmpty()) {
            ivHistoryClear.visibility = View.GONE
        }
        mSearchHistoryAdapter.refresh(searchHistory)
    }

    override fun showSearchResult(articleList: List<Article>) {

    }
}