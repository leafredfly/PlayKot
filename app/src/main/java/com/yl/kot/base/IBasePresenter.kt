package com.yl.kot.base

import io.reactivex.disposables.Disposable


/**
 * Author: Want-Sleep
 * Date: 2019/07/25
 * Desc:
 */
interface IBasePresenter<T : IBaseView> : IWindowLifecycle {
    fun attachView(view: T)

    fun detachView()

    fun getView(): IBaseView?

    fun addDisposable(disposable: Disposable)

    fun destroy()
}
