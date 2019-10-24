package com.yl.kot

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * Author: Want-Sleep
 * Date: 2019/07/25
 * Desc:
 */
class App : Application(), Application.ActivityLifecycleCallbacks {

    companion object {
        private lateinit var INSTANCE: App

        fun getInstance(): App = INSTANCE
    }

    private val mActivityStack: MutableList<Activity> = mutableListOf()
    private var mActivityCount = 0

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        mActivityStack.add(activity)
    }

    override fun onActivityStarted(activity: Activity) {
        mActivityCount++
    }

    override fun onActivityResumed(activity: Activity) {}

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {
        mActivityCount--
    }

    override fun onActivityDestroyed(activity: Activity) {
        mActivityStack.remove(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}

    /**
     * 获取栈顶Activity
     */
    fun topActivity(): Activity = mActivityStack[mActivityStack.size - 1]

    fun isForeground(): Boolean = mActivityCount > 0
}