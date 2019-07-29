package com.yl.kot.view.banner

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.yl.kot.data.entity.Banner
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Author: Want-Sleep
 * Date: 2019/07/29
 * Desc: Banner
 */

class BannerView : RecyclerView {

    private val mBannerAdapter: BannerAdapter
    private val mLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
    private var mTimerDispose: Disposable? = null

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
        mTimerDispose = Flowable.interval(5, 5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                smoothScrollToPosition(mLayoutManager.findFirstVisibleItemPosition() + 1)
            }
            .subscribe()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mTimerDispose?.dispose()
    }

    fun setBanner(bannerList: List<Banner>) {
        mBannerAdapter.refresh(bannerList)
    }
}