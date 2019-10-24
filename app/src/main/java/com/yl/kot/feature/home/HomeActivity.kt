package com.yl.kot.feature.home

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.scwang.smartrefresh.layout.header.BezierRadarHeader
import com.yl.kot.Page
import com.yl.kot.R
import com.yl.kot.base.BaseActivity
import com.yl.kot.data.entity.Article
import com.yl.kot.data.entity.Banner
import com.yl.kot.view.banner.BannerView
import com.yl.kot.view.decoration.ArticleItemDecoration

class HomeActivity : BaseActivity(), HomeContract.View, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var bannerHome: BannerView
    private lateinit var rvArticleList: RecyclerView
    private lateinit var drawer: DrawerLayout

    private val mHomePresenter: HomeContract.Presenter by lazy {
        HomePresenter(this)
    }
    private val mArticleAdapter: ArticleAdapter by lazy {
        ArticleAdapter()
    }
    private var mPage = 0

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_home_search -> Page.toSearch()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawer.closeDrawers()
        when (item.itemId) {
            R.id.menu_home_collection -> Page.toCollection()
        }
        return true
    }

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun addLifecycleObserver() {
        lifecycle.addObserver(mHomePresenter)
    }

    override fun initView() {
        refreshLayout = findViewById(R.id.refresh_home)
        bannerHome = findViewById(R.id.banner_home)
        rvArticleList = findViewById(R.id.rv_home_article_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_home)
        setSupportActionBar(toolbar)
        drawer = findViewById(R.id.drawer_home)
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        val navigationView = findViewById<NavigationView>(R.id.nav_home)
        navigationView.itemIconTintList = null
        navigationView.setNavigationItemSelectedListener(this)

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

    override fun dataLoadFail(resId: Int) {
        super.dataLoadFail(resId)
        refreshLayout.finishRefresh()
    }

    override fun reloadData() {
        refreshLayout.autoRefresh()
    }
}
