package com.yl.kot.base


/**
 * Author: Want-Sleep
 * Date: 2019/07/25
 * Desc:
 */
interface IBasePresenter<T : IBaseView> : IWindowLifecycle {
    fun attachView(view: T)

    fun detachView()

    fun destroy()
}
