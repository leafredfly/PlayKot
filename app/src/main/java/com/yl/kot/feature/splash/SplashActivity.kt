package com.yl.kot.feature.splash

import android.widget.TextView
import com.yl.kot.Page
import com.yl.kot.R
import com.yl.kot.base.BaseActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created on 2019/7/27.
 *
 * @author lshun
 * @version 1.0
 */
class SplashActivity : BaseActivity() {

    private lateinit var mTvSkip: TextView

    private lateinit var mTimerJob: Job

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun addLifecycleObserver() {}

    override fun initView() {
        mTvSkip = findViewById(R.id.tv_splash_skip)
        mTvSkip.setOnClickListener {
            jump2MainView()
        }
    }

    override fun onResume() {
        super.onResume()
        mTimerJob = MainScope().launch {
            delay(AUTO_JUMP_DELAY)
            jump2MainView()
        }
    }

    override fun onPause() {
        super.onPause()
        mTimerJob.cancel()
    }

    /**
     * 跳转到主界面
     */
    private fun jump2MainView() {
        mTimerJob.cancel()
        Page.toHome()
        finish()
    }

    companion object {
        private const val AUTO_JUMP_DELAY: Long = 3_000
    }
}