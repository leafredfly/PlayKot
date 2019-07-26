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
        fragmentLifecycleInjectIntoField("onWindowCreated")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewRoot = inflater.inflate(getLayoutId(), container, false)
        initView(viewRoot)
        return viewRoot
    }

    override fun onStart() {
        super.onStart()
        fragmentLifecycleInjectIntoField("onWindowStarted")
    }

    override fun onResume() {
        super.onResume()
        fragmentLifecycleInjectIntoField("onWindowResumed")
    }

    override fun onPause() {
        super.onPause()
        fragmentLifecycleInjectIntoField("onWindowPaused")
    }

    override fun onStop() {
        super.onStop()
        fragmentLifecycleInjectIntoField("onWindowStopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentLifecycleInjectIntoField("onWindowDestroyed")
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initView(viewRoot: View)

    private fun fragmentLifecycleInjectIntoField(methodName: String) {
        try {
            val fields = this.javaClass.declaredFields
            for (field in fields) {
                if (IWindowLifecycle::class.java.isAssignableFrom(field.type)) {
                    field.isAccessible = true
                    val method = IWindowLifecycle::class.java.getDeclaredMethod(methodName)
                    val instance = field.get(activity) as IWindowLifecycle?
                    instance?.let {
                        method.invoke(instance)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}