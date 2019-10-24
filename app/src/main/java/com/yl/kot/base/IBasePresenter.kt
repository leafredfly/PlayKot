package com.yl.kot.base

import androidx.lifecycle.LifecycleObserver


/**
 * Author: Want-Sleep
 * Date: 2019/07/25
 * Desc:
 */
interface IBasePresenter<T : IBaseView> : LifecycleObserver {
    fun attachView(view: T)

    fun detachView()

    fun destroy()
}
