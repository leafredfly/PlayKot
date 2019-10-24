package com.yl.kot.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.yl.kot.R

/**
 * Author: Want-Sleep
 * Date: 2019/07/25
 * Desc:
 */
abstract class BaseActivity : AppCompatActivity(), IBaseView {

    private val mFailLayout: ViewGroup by lazy {
        LayoutInflater.from(this)
            .inflate(
                getDataLoadFailLayoutId(),
                findViewById<FrameLayout>(android.R.id.content),
                false
            ) as ViewGroup
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        initView()
        addLifecycleObserver()
    }

    override fun dataLoadFail(@StringRes resId: Int) {
        if (!showDataLoadFailView() || mFailLayout.parent != null) return
        val rootView = findViewById<FrameLayout>(android.R.id.content)
        rootView.addView(mFailLayout)

        (mFailLayout.findViewById(R.id.tv_load_fail_cause) as TextView).text = getString(resId)
        mFailLayout.findViewById<Button>(R.id.btn_load_fail_retry).setOnClickListener {
            rootView.removeView(mFailLayout)
            reloadData()
        }
    }

    protected open fun getDataLoadFailLayoutId(): Int = R.layout.view_data_load_fail

    protected open fun showDataLoadFailView(): Boolean = true

    protected open fun reloadData() {}

    protected abstract fun getLayoutId(): Int

    protected abstract fun initView()

    protected abstract fun addLifecycleObserver()
}
