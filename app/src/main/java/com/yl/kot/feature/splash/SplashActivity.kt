package com.yl.kot.feature.splash

import android.os.Handler
import android.widget.TextView
import com.yl.kot.Page
import com.yl.kot.R
import com.yl.kot.base.BaseActivity

/**
 * Created on 2019/7/27.
 *
 * @author lshun
 * @version 1.0
 */
class SplashActivity : BaseActivity() {

    private lateinit var mTvSkip: TextView

    private val mHandler: Handler by lazy {
        Handler()
    }

    private val mRunnable: Runnable = Runnable { jump2MainView() }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        mTvSkip = findViewById(R.id.tv_skip)
        mTvSkip.setOnClickListener {
            jump2MainView()
        }
    }

    override fun onResume() {
        super.onResume()
        mHandler.removeCallbacks(mRunnable)
        mHandler.postDelayed(mRunnable, AUTO_JUMP_DELAY)
    }

    override fun onPause() {
        super.onPause()
        mHandler.removeCallbacks(mRunnable)
    }

    /**
     * 跳转到主界面
     */
    private fun jump2MainView() {
        mHandler.removeCallbacks(mRunnable)
        Page.toHome()
        finish()
    }

    companion object {
        private const val AUTO_JUMP_DELAY: Long = 3000L
    }
}