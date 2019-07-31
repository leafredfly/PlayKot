package com.yl.kot.feature.search

import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.scwang.smartrefresh.layout.header.BezierRadarHeader
import com.yl.kot.Constants
import com.yl.kot.R
import com.yl.kot.base.BaseActivity
import com.yl.kot.data.entity.Article
import com.yl.kot.data.entity.HotWord
import com.yl.kot.feature.home.ArticleAdapter
import com.yl.kot.view.decoration.ArticleItemDecoration

/**
 * Author: Want-Sleep
 * Date: 2019/07/31
 * Desc:
 */

class SearchDetailActivity : BaseActivity(), SearchContract.View {

    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var rvArticleList: RecyclerView

    private val mSearchPresenter: SearchContract.Presenter by lazy {
        SearchPresenter(this)
    }
    private val mArticleAdapter: ArticleAdapter by lazy {
        ArticleAdapter()
    }
    private var mPage = 0

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getLayoutId(): Int = R.layout.activity_search_detail

    override fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val keyword: String? = intent.getStringExtra(Constants.EXTRA_SEARCH_KEY)
        title = keyword

        refreshLayout = findViewById(R.id.refresh_search_detail)
        rvArticleList = findViewById(R.id.rv_search_detail_article_list)

        val llm = LinearLayoutManager(this)
        llm.orientation = RecyclerView.VERTICAL
        rvArticleList.layoutManager = llm
        rvArticleList.addItemDecoration(ArticleItemDecoration())
        rvArticleList.adapter = mArticleAdapter

        val refreshHeader = BezierRadarHeader(this)
        refreshHeader.setPrimaryColorId(R.color.colorPrimary)
        refreshHeader.setAccentColorId(R.color.colorAccent)
        val refreshFooter = BallPulseFooter(this)
        refreshFooter.setAnimatingColor(ContextCompat.getColor(this, R.color.colorAccent))
        refreshLayout.setRefreshHeader(refreshHeader)
        refreshLayout.setRefreshFooter(refreshFooter)
        refreshLayout.setOnRefreshListener {
            mPage = 0
            mSearchPresenter.searchArticle(keyword, mPage)
        }
        refreshLayout.setOnLoadMoreListener {
            mPage++
            mSearchPresenter.searchArticle(keyword, mPage)
        }

        mSearchPresenter.searchArticle(keyword, mPage)
    }

    override fun showHotWords(hotWordList: List<HotWord>) {

    }

    override fun showSearchResult(articleList: List<Article>) {
        if (mPage == 0) {
            refreshLayout.finishRefresh()
            mArticleAdapter.refresh(articleList)
        } else {
            refreshLayout.finishLoadMore()
            mArticleAdapter.loadMore(articleList)
        }
        if (articleList.size < 20) refreshLayout.setNoMoreData(true)
    }
}