package com.yl.kot.feature.login

import com.yl.kot.R
import com.yl.kot.base.BasePresenter
import com.yl.kot.data.remote.ApiClient
import com.yl.kot.data.remote.RemoteErrorHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 * Created on 2019/7/27.
 *
 * @author lshun
 * @version 1.0
 */
class LoginPresenter(view: LoginContract.View) : BasePresenter<LoginContract.View>(view),
    LoginContract.Presenter {

    /**
     * 用户登录
     *
     * @param username   用户名
     * @param password   密码
     */
    override fun login(username: String, password: String) {
        val checkUsernameResult= checkUsername(username)
        checkUsernameResult?.let {
            mView?.showUsernameError(it)
            return
        }

        val checkPasswordResult = checkPassword(password)
        checkPasswordResult?.let {
            mView?.showPasswordError(it)
            return
        }

        launch(CoroutineExceptionHandler{ _, throwable ->
            mView?.let {
                RemoteErrorHandler.handle(it, throwable)
                mView?.showLoginResult(null)
            }
        }) {
            mView?.showLoginResult(ApiClient.getApiService().login(username, password))
        }
    }

    /**
     * 用户注册
     *
     * @param username   用户名
     * @param password   密码
     * @param rePassword 密码重复确认
     */
    override fun register(username: String, password: String, rePassword: String) {
        val checkUsernameResult: String? = checkUsername(username)
        if (checkUsernameResult != null) {
            mView?.showUsernameError(checkUsernameResult)
            return
        }

        val checkPasswordResult: String? = checkPassword(password)
        if (checkPasswordResult != null) {
            mView?.showPasswordError(checkPasswordResult)
            return
        }

        val checkRePasswordResult: String? = checkRePassword(password, rePassword)
        if (checkRePasswordResult != null) {
            mView?.showRePasswordError(checkRePasswordResult)
            return
        }

        launch(CoroutineExceptionHandler{ _, throwable ->
            mView?.let {
                RemoteErrorHandler.handle(it, throwable)
                mView?.showRegisterResult(null)
            }
        }) {
            mView?.showRegisterResult(ApiClient.getApiService().register(username, password, rePassword))
        }
    }

    /**
     * 判断用户名是否合法
     *
     * @param username   用户名
     * @return 合法时返回<code>NULL</code>，不合法返回错误信息
     */
    override fun checkUsername(username: String): String? {
        if (username.isEmpty()) return mView?.getStringValue(R.string.error_empty_username)
        if (username.length < 4 || username.length > 16) {
            return mView?.getStringValue(R.string.error_username_length, 4, 16)
        }
        return null
    }

    /**
     * 判断密码是否合法
     *
     * @param password   密码
     * @return 合法时返回<code>NULL</code>，不合法返回错误信息
     */
    override fun checkPassword(password: String): String? {
        if (password.isEmpty()) return mView?.getStringValue(R.string.error_empty_password)
        if (password.length < 4 || password.length > 16) {
            return mView?.getStringValue(R.string.error_password_length, 4, 16)
        }
        return null
    }

    /**
     * 判断确认密码是否合法，仅判断它与第一次输入的密码是否相同即可
     *
     * @param password   密码
     * @param rePassword 密码重复确认
     * @return 合法时返回<code>NULL</code>，不合法返回错误信息
     */
    override fun checkRePassword(password: String, rePassword: String): String? {
        return if (password == rePassword) {
            null
        } else {
            mView?.getStringValue(R.string.error_twice_password_not_equals)
        }
    }
}