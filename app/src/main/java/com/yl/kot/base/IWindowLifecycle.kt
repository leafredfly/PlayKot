package com.yl.kot.base

/**
 * Author: Want-Sleep
 * Date: 2019/07/25
 * Desc: 在Application类和BaseFragment类里通过反射调用
 *      window代表activity或者Fragment
 */
interface IWindowLifecycle {
    fun onWindowCreated()

    fun onWindowStarted()

    fun onWindowResumed()

    fun onWindowPaused()

    fun onWindowStopped()

    fun onWindowDestroyed()
}
