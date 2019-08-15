package com.yl.kot.view.banner

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.yl.kot.data.entity.Banner
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch

/**
 * Author: Want-Sleep
 * Date: 2019/07/29
 * Desc: Banner
 */

@ObsoleteCoroutinesApi
class BannerView : RecyclerView {

    private val mBannerAdapter: BannerAdapter
    private val mLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
    private val mTickerChannel = ticker(5_000, 5_000)
    private var mTimerJob: Job? = null

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
        mTimerJob = MainScope().launch {
            for (it in mTickerChannel) {
                smoothScrollToPosition(mLayoutManager.findFirstVisibleItemPosition() + 1)
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mTickerChannel.cancel()
        mTimerJob?.cancel()
    }

    fun setBanner(bannerList: List<Banner>) {
        mBannerAdapter.refresh(bannerList)
    }
}