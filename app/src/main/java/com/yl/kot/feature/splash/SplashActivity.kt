package com.yl.kot.feature.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.ImageView
import com.yl.kot.R
import com.yl.kot.base.BaseActivity
import com.yl.kot.feature.home.MainActivity

/**
 * Created on 2019/7/27.
 *
 * @author lshun
 * @version 1.0
 */
class SplashActivity : BaseActivity() {
    private lateinit var mIvWelcome: ImageView

    private val mHandler: Handler by lazy {
        Handler()
    }

    private val mRunnable: Runnable = Runnable {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    }

    override fun initView() {
        mIvWelcome = findViewById(R.id.iv_welcome)
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

    companion object {
        private const val AUTO_JUMP_DELAY: Long = 3000L
    }
}