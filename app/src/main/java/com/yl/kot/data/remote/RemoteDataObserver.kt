package com.yl.kot.data.remote

import com.google.gson.JsonSyntaxException
import com.yl.kot.R
import com.yl.kot.base.IBasePresenter
import com.yl.kot.base.IBaseView
import com.yl.kot.utils.SingleToast
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.io.IOException
import java.lang.ref.WeakReference
import java.net.SocketTimeoutException

/**
 * Author: Want-Sleep
 * Date: 2019/07/26
 * Desc:
 */

open class RemoteDataObserver<T>(private val basePresenter: IBasePresenter<*>) : Observer<T> {

    private val mBaseViewRef: WeakReference<IBaseView?> = WeakReference(basePresenter.getView())

    override fun onSubscribe(disposable: Disposable) {
        basePresenter.addDisposable(disposable)
    }

    override fun onNext(t: T) {}

    override fun onError(throwable: Throwable) {
        handleError(throwable)
    }

    override fun onComplete() {}

    private fun handleError(throwable: Throwable) {
        if (mBaseViewRef.get() == null) {
            return
        }
        when (throwable) {
            is ApiException -> handleApiError(throwable)
            is SocketTimeoutException -> SingleToast.showToast(R.string.error_timeout)
            is JsonSyntaxException -> SingleToast.showToast(R.string.error_json_syntax)
            is IOException -> SingleToast.showToast(R.string.error_network)
            is HttpException -> SingleToast.showToast(throwable.message())
            else -> SingleToast.showToast(R.string.error_unknown)
        }
    }

    private fun handleApiError(apiException: ApiException) {
        SingleToast.showToast(apiException.errorMsg)
    }
}