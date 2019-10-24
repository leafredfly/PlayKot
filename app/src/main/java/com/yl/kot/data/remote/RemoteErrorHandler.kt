package com.yl.kot.data.remote

import com.google.gson.JsonSyntaxException
import com.yl.kot.R
import com.yl.kot.base.IBaseView
import com.yl.kot.utils.AwesomeSnackBar
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Author: Want-Sleep
 * Date: 2019/08/15
 * Desc:
 */
object RemoteErrorHandler {

    fun handle(baseView: IBaseView, throwable: Throwable) {
        when (throwable) {
            is ApiException -> handleApiError(throwable)
            is SocketTimeoutException -> AwesomeSnackBar.show(R.string.error_timeout)
            is JsonSyntaxException -> AwesomeSnackBar.show(R.string.error_json_syntax)
            is IOException -> {
                baseView.dataLoadFail(R.string.error_network)
                AwesomeSnackBar.show(R.string.error_network)
            }
            is HttpException -> AwesomeSnackBar.show(throwable.message())
            else -> {
                throwable.printStackTrace()
                baseView.dataLoadFail(R.string.error_unknown)
                AwesomeSnackBar.show(R.string.error_unknown)
            }
        }
    }

    private fun handleApiError(apiException: ApiException) {
        when (apiException.errorCode) {
            else -> AwesomeSnackBar.show(apiException.errorMsg)
        }
    }
}