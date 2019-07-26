package com.yl.kot.feature.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yl.kot.R
import com.yl.kot.data.entity.Banner

class MainActivity : AppCompatActivity(), HomeContract.View {

    private val mHomePresenter : HomeContract.Presenter by lazy {
        HomePresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mHomePresenter.getBanner()
    }

    override fun showBanner(bannerList: List<Banner>) {

    }
}
