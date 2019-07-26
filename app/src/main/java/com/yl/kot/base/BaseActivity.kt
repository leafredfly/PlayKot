package com.yl.kot.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Author: Want-Sleep
 * Date: 2019/07/25
 * Desc:
 */
abstract class BaseActivity : AppCompatActivity(), IBaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        initView()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initView()
}
