package com.yl.kot.data.remote

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.google.gson.JsonSyntaxException
import com.yl.kot.App
import com.yl.kot.Page
import com.yl.kot.R
import com.yl.kot.base.IBasePresenter
import com.yl.kot.base.IBaseView
import com.yl.kot.feature.login.LoginActivity
import com.yl.kot.utils.AwesomeSnackBar
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

    override fun onNext(response: T) {}

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
            is SocketTimeoutException -> AwesomeSnackBar.show(R.string.error_timeout)
            is JsonSyntaxException -> AwesomeSnackBar.show(R.string.error_json_syntax)
            is IOException -> {
                mBaseViewRef.get()?.dataLoadFail(R.string.error_network)
                AwesomeSnackBar.show(R.string.error_network)
            }
            is HttpException -> AwesomeSnackBar.show(throwable.message())
            else -> {
                mBaseViewRef.get()?.dataLoadFail(R.string.error_unknown)
                AwesomeSnackBar.show(R.string.error_unknown)
            }
        }
    }

    private fun handleApiError(apiException: ApiException) {
        when (apiException.errorCode) {
            ApiException.CODE_NO_LOGIN -> {
                val activity: Activity = App.getInstance().topActivity()
                val isForeground = App.getInstance().isForeground()
                if (isForeground && activity.javaClass != LoginActivity::class.java) {
                    AlertDialog.Builder(activity)
                        .setTitle(null)
                        .setMessage(R.string.error_not_login)
                        .setPositiveButton(R.string.login_now) { _, _ ->
                            Page.toLogin()
                        }
                        .setNegativeButton(android.R.string.cancel, null)
                        .show()
                }
            }
            else -> AwesomeSnackBar.show(apiException.errorMsg)
        }
    }
}