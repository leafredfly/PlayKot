package com.yl.kot.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Author: Want-Sleep
 * Date: 2019/07/25
 * Desc:
 */
abstract class BaseFragment : Fragment(), IBaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addLifecycleObserver()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewRoot = inflater.inflate(getLayoutId(), container, false)
        initView(viewRoot)
        return viewRoot
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initView(viewRoot: View)

    protected abstract fun addLifecycleObserver()
}