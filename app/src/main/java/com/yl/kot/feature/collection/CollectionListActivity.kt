package com.yl.kot.feature.collection

import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.scwang.smartrefresh.layout.header.BezierRadarHeader
import com.yl.kot.R
import com.yl.kot.base.BaseActivity
import com.yl.kot.data.entity.Article
import com.yl.kot.view.decoration.ArticleItemDecoration

/**
 * Author: Want-Sleep
 * Date: 2019/10/24
 * Desc:
 */
class CollectionListActivity: BaseActivity(), CollectionContract.View {

    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var rvArticleList: RecyclerView

    private val mCollectionPresenter: CollectionContract.Presenter by lazy {
        CollectionPresenter(this)
    }
    private val mArticleAdapter: CollectionArticleAdapter by lazy {
        CollectionArticleAdapter()
    }
    private var mPage: Int = 0

    override fun getLayoutId(): Int = R.layout.activity_collection_list

    override fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        refreshLayout = findViewById(R.id.refresh_collection_list)
        rvArticleList = findViewById(R.id.rv_collection_article_list)

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
            mCollectionPresenter.getCollectionArticlesByPage(mPage)
        }
        refreshLayout.setOnLoadMoreListener {
            mPage++
            mCollectionPresenter.getCollectionArticlesByPage(mPage)
        }

        mCollectionPresenter.getCollectionArticlesByPage(0)
    }

    override fun addLifecycleObserver() {
        lifecycle.addObserver(mCollectionPresenter)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onArticleHasCollected(hasCollected: Boolean) {}

    override fun showArticle(articleList: List<Article>) {
        if (mPage == 0) {
            refreshLayout.finishRefresh()
            mArticleAdapter.refresh(articleList)
        } else {
            refreshLayout.finishLoadMore()
            mArticleAdapter.loadMore(articleList)
        }
        if (articleList.size < 10) refreshLayout.setNoMoreData(true)
    }
}