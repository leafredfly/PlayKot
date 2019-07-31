package com.yl.kot

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.yl.kot.base.IWindowLifecycle

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
        activityLifecycleInjectIntoField(activity, "onWindowCreated")
    }

    override fun onActivityStarted(activity: Activity) {
        activityLifecycleInjectIntoField(activity, "onWindowStarted")
        mActivityCount++
    }

    override fun onActivityResumed(activity: Activity) {
        activityLifecycleInjectIntoField(activity, "onWindowResumed")
    }

    override fun onActivityPaused(activity: Activity) {
        activityLifecycleInjectIntoField(activity, "onWindowPaused")
    }

    override fun onActivityStopped(activity: Activity) {
        activityLifecycleInjectIntoField(activity, "onWindowStopped")
        mActivityCount--
    }

    override fun onActivityDestroyed(activity: Activity) {
        mActivityStack.remove(activity)
        activityLifecycleInjectIntoField(activity, "onWindowDestroyed")
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

    }

    /**
     * 获取栈顶Activity
     */
    fun topActivity(): Activity = mActivityStack[mActivityStack.size - 1]

    fun isForeground(): Boolean = mActivityCount > 0

    private fun activityLifecycleInjectIntoField(activity: Activity, methodName: String) {
        try {
            val fields = activity.javaClass.declaredFields
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