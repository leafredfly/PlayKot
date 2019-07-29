package com.yl.kot.feature.home

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.yl.kot.R
import com.yl.kot.base.BaseActivity
import com.yl.kot.data.entity.Article
import com.yl.kot.data.entity.Banner
import com.yl.kot.view.banner.BannerView

class HomeActivity : BaseActivity(), HomeContract.View {

    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var bannerHome: BannerView
    private lateinit var rvArticleList: RecyclerView

    private val mHomePresenter: HomeContract.Presenter by lazy {
        HomePresenter(this)
    }
    private val mArticleAdapter: ArticleAdapter by lazy {
        ArticleAdapter()
    }
    private var mPage = 0

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun initView() {
        refreshLayout = findViewById(R.id.refresh_home)
        bannerHome = findViewById(R.id.banner_home)
        rvArticleList = findViewById(R.id.rv_home_article_list)

        val llm = LinearLayoutManager(this)
        llm.orientation = RecyclerView.VERTICAL
        rvArticleList.layoutManager = llm
        rvArticleList.adapter = mArticleAdapter

        refreshLayout.setOnRefreshListener {
            mPage = 0
            mHomePresenter.getArticle(mPage)
            mHomePresenter.getBanner()
        }
        refreshLayout.setOnLoadMoreListener {
            mPage++
            mHomePresenter.getArticle(mPage)
        }

        mHomePresenter.getBanner()
        mHomePresenter.getArticle(0)
    }

    override fun showBanner(bannerList: List<Banner>) {
        bannerHome.setBanner(bannerList)
    }

    override fun showArticle(articleList: List<Article>) {
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
