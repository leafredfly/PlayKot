package com.yl.kot.view.banner

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.yl.kot.data.entity.Banner

/**
 * Author: Want-Sleep
 * Date: 2019/07/29
 * Desc: Banner
 */

class BannerView : RecyclerView {

    private val mBannerAdapter: BannerAdapter
    private val mLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
    private val mScrollRunnable: Runnable = Runnable {
        smoothScrollToPosition(mLayoutManager.findFirstVisibleItemPosition() + 1)
        authScrollAfterSeconds()
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    init {
        // set layout manager
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        layoutManager = mLayoutManager
        // set snap helper
        PagerSnapHelper().attachToRecyclerView(this)
        // set adapter
        mBannerAdapter = BannerAdapter()
        adapter = mBannerAdapter
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        // set timer
        authScrollAfterSeconds()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removeCallbacks(mScrollRunnable)
    }

    fun setBanner(bannerList: List<Banner>) {
        mBannerAdapter.refresh(bannerList)
    }

    private fun authScrollAfterSeconds() {
        postDelayed(mScrollRunnable, 5_000)
    }
}