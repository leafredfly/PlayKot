package com.yl.kot.feature.home

import com.yl.kot.base.IBasePresenter
import com.yl.kot.base.IBaseView
import com.yl.kot.data.entity.Article
import com.yl.kot.data.entity.Banner

/**
 * Author: Want-Sleep
 * Date: 2019/07/26
 * Desc:
 */

interface HomeContract {

    interface View : IBaseView {
        /**
         * 显示Banner
         *
         */
        fun showBanner(bannerList: List<Banner>)
        /**
         * 显示文章列表
         *
         */
        fun showArticle(articleList: List<Article>)
    }

    interface Presenter : IBasePresenter<View> {
        /**
         * 获取Banner
         *
         */
        fun getBanner()
        /**
         * 获取文章列表
         *
         * @param page start form 0
         */
        fun getArticle(page: Int)
        /**
         * 获取首页置顶文章列表
         *
         */
        fun getTopArticle()
    }
}