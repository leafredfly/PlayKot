package com.yl.kot.feature.home

import com.yl.kot.base.IBasePresenter
import com.yl.kot.base.IBaseView
import com.yl.kot.data.entity.Banner

/**
 * Author: Want-Sleep
 * Date: 2019/07/26
 * Desc:
 */

interface HomeContract {

    interface View : IBaseView {
        fun showBanner(bannerList: List<Banner>)
    }

    interface Presenter : IBasePresenter<View> {
        fun getBanner()
    }
}